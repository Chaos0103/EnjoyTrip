package hotplace.repository;

import hotplace.HotPlace;
import hotplace.dto.HotPlaceSearch;

import java.util.List;
import java.util.Optional;

public interface HotPlaceRepository {

    int save(HotPlace hotPlace);

    Optional<HotPlace> findById(Long hotPlaceId);

    List<HotPlace> findByCondition(HotPlaceSearch condition);

    int update(HotPlace hotPlace);

    int updateHit(HotPlace hotPlace);

    int remove(Long hotPlaceId);

    void clear();
}
