package notion;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Notion {

    private Long notionId;
    private String title;
    private String content;
    private int hit;
    private boolean top;
    private Long createdBy;
    private Long lastModifiedBy;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    @Builder
    public Notion(Long notionId, String title, String content, int hit, boolean top, Long createdBy, Long lastModifiedBy, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
        this.notionId = notionId;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.top = top;
        this.createdBy = createdBy;
        this.lastModifiedBy = lastModifiedBy;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }
}
