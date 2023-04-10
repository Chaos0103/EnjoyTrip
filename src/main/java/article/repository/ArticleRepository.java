package article.repository;

import article.Article;
import article.dto.ArticleSearch;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {

    int save(Article article);

    Optional<Article> findById(Long articleId);

    List<Article> findByMemberId(Long memberId);

    List<Article> findByCondition(ArticleSearch condition);

    int findTotalCount();

    int update(Article article);

    int updateHit(Article article);

    int remove(Long articleId);

    void clear();
}
