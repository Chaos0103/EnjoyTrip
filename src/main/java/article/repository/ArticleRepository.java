package article.repository;

import article.Article;
import article.dto.ArticleDto;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {

    int save(Long memberId, ArticleDto articleDto);

    Optional<Article> findById(Long articleId);

    List<Article> findByMemberId(Long memberId);

    int update(Long articleId, ArticleDto articleDto);

    void clear();
}
