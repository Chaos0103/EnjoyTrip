package notion.repository;

import notion.dto.NotionDto;

public interface NotionRepository {

    int save(Long memberId, NotionDto notionDto);

    void clear();
}
