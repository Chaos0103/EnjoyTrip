package hotplace.service;

import hotplace.dto.HotPlaceDto;
import hotplace.dto.HotPlaceListDto;
import hotplace.dto.HotPlaceSearch;

import java.util.List;

public interface HotPlaceService {

    int addHotPlace(Long memberId, int contentId, HotPlaceDto hotPlaceDto);

    List<HotPlaceListDto> searchHotPlace(HotPlaceSearch condition);

    int removeHotPlace(Long hotPlaceId, Long memberId);
}
