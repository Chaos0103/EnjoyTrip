package article;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import member.Member;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {

    private Long articleId;
    private String title;
    private String content;
    private int hit;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    private Member member;

    @Builder
    public Article(Long articleId, String title, String content, int hit, LocalDateTime createdDate, LocalDateTime lastModifiedDate, Member member) {
        this.articleId = articleId;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.member = member;
    }
}
