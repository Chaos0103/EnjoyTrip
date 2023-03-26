package attraction.repository;

import attraction.AttractionInfo;
import attraction.dto.AttractionSearch;

import java.util.List;

public interface AttractionRepository {

    List<AttractionInfo> findByConditions(AttractionSearch condition);

}
