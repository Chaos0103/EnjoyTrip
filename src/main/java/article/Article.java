package article;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Article {

    private Long articleId;
    private Long memberId;
    private String title;
    private String content;
    private int hit;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

}
