package article.service;

import article.dto.ArticleDto;

public interface ArticleService {

    int addArticle(Long memberId, ArticleDto articleDto);

    int editArticle(Long articleId, Long memberId, ArticleDto articleDto);
}
