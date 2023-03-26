package attraction.service;

import attraction.dto.AttractionDto;
import attraction.dto.AttractionSearch;

import java.util.List;

public interface AttractionService {

    List<AttractionDto> searchAttraction(AttractionSearch condition);

}
