package notion;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Notion {

    private Long notionId;
    private Long memberId;
    private String title;
    private String content;
    private int hit;
    private boolean top;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    @Builder
    public Notion(Long notionId, Long memberId, String title, String content, int hit, boolean top, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
        this.notionId = notionId;
        this.memberId = memberId;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.top = top;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }
}
