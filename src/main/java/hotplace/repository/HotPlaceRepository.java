package hotplace.repository;

import hotplace.HotPlace;

import java.util.Optional;

public interface HotPlaceRepository {

    int save(Long memberId, int contentId, HotPlace hotPlace);

    Optional<HotPlace> findById(Long hotPlaceId);

    int remove(Long hotPlaceId);
}
