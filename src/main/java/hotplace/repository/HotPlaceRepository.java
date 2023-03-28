package hotplace.repository;

import hotplace.HotPlace;

import java.util.Optional;

public interface HotPlaceRepository {

    int save(HotPlace hotPlace);

    Optional<HotPlace> findById(Long hotPlaceId);
}
