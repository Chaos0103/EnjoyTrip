package article.service;

import article.dto.ArticleDto;

public interface ArticleService {

    void addArticle(Long memberId, ArticleDto articleDto);
}
