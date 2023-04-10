package hotplace.service;

import attraction.AttractionInfo;
import attraction.repository.AttractionJdbcRepository;
import attraction.repository.AttractionRepository;
import common.FileStore;
import common.exception.HotPlaceException;
import hotplace.HotPlace;
import hotplace.UploadFile;
import hotplace.dto.HotPlaceDto;
import hotplace.dto.HotPlaceListDto;
import hotplace.dto.HotPlaceSearch;
import hotplace.repository.HotPlaceJdbcRepository;
import hotplace.repository.HotPlaceQueryJdbcRepository;
import hotplace.repository.HotPlaceQueryRepository;
import hotplace.repository.HotPlaceRepository;
import member.Member;
import member.repository.MemberJdbcRepository;
import member.repository.MemberRepository;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class HotPlaceServiceImpl implements HotPlaceService {

    private static final HotPlaceService hotPlaceService = new HotPlaceServiceImpl();

    private final HotPlaceRepository hotPlaceRepository;
    private final HotPlaceQueryRepository hotPlaceQueryRepository;
    private final MemberRepository memberRepository;
    private final AttractionRepository attractionRepository;

    private HotPlaceServiceImpl() {
        hotPlaceRepository = HotPlaceJdbcRepository.getHotPlaceRepository();
        hotPlaceQueryRepository = HotPlaceQueryJdbcRepository.getHotPlaceQueryRepository();
        memberRepository = MemberJdbcRepository.getMemberRepository();
        attractionRepository = AttractionJdbcRepository.getAttractionRepository();
    }

    public static HotPlaceService getHotPlaceService() {
        return hotPlaceService;
    }

    @Override
    public int addHotPlace(Long memberId, int contentId, HotPlaceDto hotPlaceDto) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        if (!findMember.isPresent()) {
            throw new HotPlaceException();
        }

        Optional<AttractionInfo> findAttractionInfo = attractionRepository.findById(contentId);
        if (!findAttractionInfo.isPresent()) {
            throw new HotPlaceException();
        }

        HotPlace hotPlace = HotPlace.builder()
                .contentTypeId(hotPlaceDto.getContentTypeId())
                .name(hotPlaceDto.getName())
                .desc(hotPlaceDto.getDesc())
                .visitedDate(hotPlaceDto.getVisitedDate())
                .uploadFile(
                        UploadFile.builder()
                                .uploadFileName(hotPlaceDto.getUploadFile().getUploadFileName())
                                .storeFileName(hotPlaceDto.getUploadFile().getStoreFileName())
                                .build()
                )
                .member(findMember.get())
                .attractionInfo(findAttractionInfo.get())
                .build();

        return hotPlaceRepository.save(hotPlace);
    }

    @Override
    public HotPlaceDto searchHotPlace(Long hotPlaceId) {
        Optional<HotPlace> findHotPlace = hotPlaceRepository.findById(hotPlaceId);
        if (!findHotPlace.isPresent()) {
            throw new HotPlaceException();
        }

        HotPlace hotPlace = findHotPlace.get();

        return HotPlaceDto.builder()
                .id(hotPlace.getId())
                .name(hotPlace.getName())
                .desc(hotPlace.getDesc())
                .hit(hotPlace.getHit())
                .uploadFile(UploadFile.builder().storeFileName(hotPlace.getUploadFile().getStoreFileName()).build())
                .createdDate(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(hotPlace.getCreatedDate()))
                .build();
    }

    @Override
    public List<HotPlaceListDto> searchHotPlaces(HotPlaceSearch condition) {
        List<HotPlace> hotPlaces = hotPlaceQueryRepository.findByCondition(condition);
        FileStore fileStore = new FileStore();

        return hotPlaces.stream()
                .map(hotPlace -> HotPlaceListDto.builder()
                        .id(hotPlace.getId())
                        .name(hotPlace.getName())
                        .desc(hotPlace.getDesc())
                        .hit(hotPlace.getHit())
                        .storeFileName(hotPlace.getUploadFile().getStoreFileName())
                        .nickname(hotPlace.getMember().getNickname())
                        .createdDate(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(hotPlace.getCreatedDate()))
                        .build()
                )
                .collect(Collectors.toList());
    }

    @Override
    public int editHotPlace(Long memberId, Long hotPlaceId, HotPlaceDto hotPlaceDto) {
        Optional<HotPlace> findHotPlace = hotPlaceRepository.findById(hotPlaceId);
        if (!findHotPlace.isPresent()) {
            throw new HotPlaceException();
        }

        HotPlace hotPlace = findHotPlace.get();
        if (!hotPlace.getMember().getId().equals(memberId)) {
            throw new HotPlaceException();
        }

        hotPlace.editContent(hotPlace.getName(), hotPlace.getDesc(), hotPlace.getVisitedDate());

        return hotPlaceRepository.update(hotPlace);
    }

    @Override
    public int updateHit(Long hotPlaceId) {
        Optional<HotPlace> findHotPlace = hotPlaceRepository.findById(hotPlaceId);
        if (!findHotPlace.isPresent()) {
            throw new HotPlaceException();
        }

        HotPlace hotPlace = findHotPlace.get();
        hotPlace.increaseHit();

        return hotPlaceRepository.updateHit(hotPlace);
    }

    @Override
    public int removeHotPlace(Long hotPlaceId, Long memberId) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        if (!findMember.isPresent()) {
            throw new HotPlaceException();
        }

        Optional<HotPlace> findHotPlace = hotPlaceRepository.findById(hotPlaceId);
        if (!findHotPlace.isPresent()) {
            throw new HotPlaceException();
        }
        HotPlace hotPlace = findHotPlace.get();
        if (hotPlace.getMember().getId().equals(memberId)) {
            throw new HotPlaceException();
        }

        return hotPlaceRepository.remove(hotPlaceId);
    }
}
