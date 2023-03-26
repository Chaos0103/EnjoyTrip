package notion.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class NotionDto {

    private String title;
    private String content;
    private boolean top;

    @Builder
    public NotionDto(String title, String content, boolean top) {
        this.title = title;
        this.content = content;
        this.top = top;
    }
}
