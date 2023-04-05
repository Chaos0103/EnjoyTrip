package hotplace.service;

import attraction.AttractionInfo;
import attraction.repository.AttractionJdbcRepository;
import attraction.repository.AttractionRepository;
import common.exception.HotPlaceException;
import hotplace.HotPlace;
import hotplace.UploadFile;
import hotplace.dto.HotPlaceDto;
import hotplace.repository.HotPlaceJdbcRepository;
import hotplace.repository.HotPlaceRepository;
import member.Member;
import member.repository.MemberJdbcRepository;
import member.repository.MemberRepository;

import java.util.Optional;

public class HotPlaceServiceImpl implements HotPlaceService {

    private static final HotPlaceService hotPlaceService = new HotPlaceServiceImpl();

    private final HotPlaceRepository hotPlaceRepository;
    private final MemberRepository memberRepository;
    private final AttractionRepository attractionRepository;

    private HotPlaceServiceImpl() {
        hotPlaceRepository = HotPlaceJdbcRepository.getHotPlaceRepository();
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
                .build();

        return hotPlaceRepository.save(memberId, contentId, hotPlace);
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
