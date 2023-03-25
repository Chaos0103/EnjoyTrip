package article.service;

import article.dto.ArticleDto;

public interface ArticleService {

    int addArticle(Long memberId, ArticleDto articleDto);
}
