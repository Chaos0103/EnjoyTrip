package article.repository;

import article.dto.ArticleDto;

public interface ArticleRepository {

    int save(Long memberId, ArticleDto articleDto);

    void clear();
}
