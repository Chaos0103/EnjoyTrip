package article.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class ArticleDto {

    private Long memberId;
    private String title;
    private String content;

    @Builder
    public ArticleDto(Long memberId, String title, String content) {
        this.memberId = memberId;
        this.title = title;
        this.content = content;
    }
}
