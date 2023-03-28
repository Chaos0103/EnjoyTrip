package article.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleDto {

    private Long articleId;
    private String title;
    private String content;
    private int hit;
    private LocalDateTime createdDate;

    @Builder
    public ArticleDto(Long articleId, String title, String content, int hit, LocalDateTime createdDate) {
        this.articleId = articleId;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.createdDate = createdDate;
    }
}
