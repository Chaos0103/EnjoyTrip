package article.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class ArticleSearch {
    private String title;
    private String content;
    private String writer;
    private String hit;
    private String createdDate;

    @Builder
    public ArticleSearch(String title, String content, String writer, String hit, String createdDate) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.hit = hit;
        this.createdDate = createdDate;
    }
}
