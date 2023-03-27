package notion.service;

import notion.dto.NotionDto;

import java.util.List;

public interface NotionService {

    int addNotion(Long memberId, NotionDto notionDto);

    List<NotionDto> searchNotions(int pageNum, int amount);

    int getTotalCount();

    int editNotion(Long notionId, Long memberId, NotionDto notionDto);

    int removeNotion(Long notionId, Long memberId);
}
