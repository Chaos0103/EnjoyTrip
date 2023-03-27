package notion.repository;

import notion.Notion;
import notion.dto.NotionDto;

import java.util.List;
import java.util.Optional;

public interface NotionRepository {

    int save(Long memberId, NotionDto notionDto);

    Optional<Notion> findById(Long notionId);

    List<Notion> findAll();

    List<Notion> findByPaging(int pageNum, int amount);

    int getTotalCount();

    int update(Long notionId, Notion notion);

    int remove(Long notionId);

    void clear();
}
