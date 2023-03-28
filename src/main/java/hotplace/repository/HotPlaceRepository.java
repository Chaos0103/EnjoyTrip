package hotplace.repository;

import hotplace.HotPlace;

import java.util.Optional;

public interface HotPlaceRepository {

    int save(Long memberId, Long contentId, HotPlace hotPlace);

    Optional<HotPlace> findById(Long hotPlaceId);
}
