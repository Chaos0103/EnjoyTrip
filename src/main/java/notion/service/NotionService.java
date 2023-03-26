package notion.service;

import notion.dto.NotionDto;

public interface NotionService {

    int addNotion(Long memberId, NotionDto notionDto);
}
