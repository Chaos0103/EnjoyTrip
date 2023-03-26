package notion.service;

import notion.dto.NotionDto;

public interface NotionService {

    int addNotion(Long memberId, NotionDto notionDto);

    int editNotion(Long notionId, Long memberId, NotionDto notionDto);

    int removeNotion(Long notionId, Long memberId);
}
