package article.repository;

import article.Article;
import article.dto.ArticleDto;
import article.dto.ArticleSearch;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {

    int save(Long memberId, ArticleDto articleDto);

    Optional<Article> findById(Long articleId);

    List<Article> findByMemberId(Long memberId);

    List<Article> findByCondition(ArticleSearch condition);

    int findTotalCount();

    int update(Long articleId, ArticleDto articleDto);

    int remove(Long articleId);

    void clear();
}
