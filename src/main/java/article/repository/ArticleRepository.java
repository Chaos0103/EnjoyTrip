package article.repository;

import article.Article;
import article.dto.ArticleDto;

import java.util.List;

public interface ArticleRepository {

    int save(Long memberId, ArticleDto articleDto);

    List<Article> findByMemberId(Long memberId);

    int update(Long articleId, ArticleDto articleDto);

    void clear();
}
