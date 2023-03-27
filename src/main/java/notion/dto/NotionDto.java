package notion.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class NotionDto {

    private Long id;
    private String title;
    private String content;
    private boolean top;

    @Builder
    public NotionDto(Long id, String title, String content, boolean top) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.top = top;
    }
}
