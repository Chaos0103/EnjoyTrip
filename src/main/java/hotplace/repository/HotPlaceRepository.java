package hotplace.repository;

import hotplace.HotPlace;
import hotplace.dto.HotPlaceSearch;

import java.util.List;
import java.util.Optional;

public interface HotPlaceRepository {

    int save(Long memberId, int contentId, HotPlace hotPlace);

    Optional<HotPlace> findById(Long hotPlaceId);

    List<HotPlace> findByCondition(HotPlaceSearch condition);

    int update(Long hotPlaceId, HotPlace hotPlace);

    int updateHit(Long hotPlaceId, HotPlace hotPlace);

    int remove(Long hotPlaceId);

    void clear();
}
