package hotplace.service;

import hotplace.dto.HotPlaceDto;
import hotplace.dto.HotPlaceListDto;
import hotplace.dto.HotPlaceSearch;

import java.util.List;

public interface HotPlaceService {

    int addHotPlace(Long memberId, int contentId, HotPlaceDto hotPlaceDto);

    HotPlaceDto searchHotPlace(Long hotPlaceId);

    List<HotPlaceListDto> searchHotPlaces(HotPlaceSearch condition);

    int editHotPlace(Long memberId, Long hotPlaceId, HotPlaceDto hotPlaceDto);

    int updateHit(Long hotPlaceId);

    int removeHotPlace(Long hotPlaceId, Long memberId);
}
