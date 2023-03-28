package attraction.repository;

import attraction.AttractionInfo;
import attraction.dto.AttractionSearch;

import java.util.List;
import java.util.Optional;

public interface AttractionRepository {

    Optional<AttractionInfo> findById(int contentId);

    List<AttractionInfo> findByConditions(AttractionSearch condition);

}
