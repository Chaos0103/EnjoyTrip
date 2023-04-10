package article.service;

import article.dto.ArticleDto;
import article.dto.ArticleSearch;

import java.util.List;

public interface ArticleService {

    int addArticle(Long memberId, ArticleDto articleDto);

    ArticleDto searchArticle(Long articleId);

    List<ArticleDto> searchArticles(ArticleSearch condition);

    int getTotalCount();

    int editArticle(Long articleId, Long memberId, ArticleDto articleDto);

    int increaseHit(Long articleId);

    int removeArticle(Long articleId, Long memberId);
}
