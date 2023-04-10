package hotplace.repository;

import hotplace.dto.HotPlaceListDto;
import hotplace.dto.HotPlaceSearch;

import java.util.List;

public interface HotPlaceQueryRepository {

    List<HotPlaceListDto> findByCondition(HotPlaceSearch condition);

}
