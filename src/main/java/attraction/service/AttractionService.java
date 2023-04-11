package attraction.service;

import attraction.dto.AttractionDto;
import attraction.dto.AttractionSearch;

import java.util.List;

public interface AttractionService {

    AttractionDto searchAttraction(int contentId);

    List<AttractionDto> searchAttraction(AttractionSearch condition);

    List<AttractionDto> searchAttraction(String title);
}
