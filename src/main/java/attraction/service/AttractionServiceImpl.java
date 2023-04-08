package attraction.service;

import attraction.AttractionInfo;
import attraction.dto.AttractionDto;
import attraction.dto.AttractionSearch;
import attraction.repository.AttractionJdbcRepository;
import attraction.repository.AttractionRepository;

import java.util.List;
import java.util.stream.Collectors;

public class AttractionServiceImpl implements AttractionService {

    private static final AttractionService attractionService = new AttractionServiceImpl();
    private final AttractionRepository attractionRepository;

    private AttractionServiceImpl() {
        attractionRepository = AttractionJdbcRepository.getAttractionRepository();
    }

    public static AttractionService getAttractionService() {
        return attractionService;
    }

    @Override
    public List<AttractionDto> searchAttraction(AttractionSearch condition) {
        List<AttractionInfo> findAttractionInfos = attractionRepository.findByConditions(condition);
        return findAttractionInfos.stream()
                .map(attractionInfo ->
                        AttractionDto.builder()
                                .title(attractionInfo.getTitle())
                                .addr1(attractionInfo.getAddr1())
                                .zipcode(attractionInfo.getZipcode())
                                .firstImage(attractionInfo.getFirstImage())
                                .latitude(attractionInfo.getLatitude())
                                .longitude(attractionInfo.getLongitude())
                                .build()
                )
                .collect(Collectors.toList());
    }

    @Override
    public List<AttractionDto> searchAttraction(String title) {
        List<AttractionInfo> findAttractionInfos = attractionRepository.findByTitle(title);
        return findAttractionInfos.stream()
                .map(attractionInfo ->
                        AttractionDto.builder()
                                .id(attractionInfo.getId())
                                .contentTypeId(attractionInfo.getContentTypeId())
                                .title(attractionInfo.getTitle())
                                .addr1(attractionInfo.getAddr1())
                                .zipcode(attractionInfo.getZipcode())
                                .firstImage(attractionInfo.getFirstImage())
                                .latitude(attractionInfo.getLatitude())
                                .longitude(attractionInfo.getLongitude())
                                .build()
                )
                .collect(Collectors.toList());
    }
}
