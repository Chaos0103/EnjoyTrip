package hotplace.service;

import hotplace.dto.HotPlaceDto;

public interface HotPlaceService {

    int addHotPlace(Long memberId, int contentId, HotPlaceDto hotPlaceDto);
}
