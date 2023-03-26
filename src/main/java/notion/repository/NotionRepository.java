package notion.repository;

import notion.Notion;
import notion.dto.NotionDto;

import java.util.Optional;

public interface NotionRepository {

    int save(Long memberId, NotionDto notionDto);

    Optional<Notion> findById(Long notionId);

    int update(Long notionId, Notion notion);

    void clear();
}
