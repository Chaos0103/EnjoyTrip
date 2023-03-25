package article.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class ArticleDto {

    private String title;
    private String content;

    @Builder
    public ArticleDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
