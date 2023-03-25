package article.repository;

import article.dto.ArticleDto;

public interface ArticleRepository {

    int save(Long memberId, ArticleDto articleDto);

    int update(Long articleId, ArticleDto articleDto);

    void clear();
}
