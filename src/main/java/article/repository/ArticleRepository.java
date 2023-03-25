package article.repository;

import article.dto.ArticleDto;

public interface ArticleRepository {

    int save(ArticleDto articleDto);

    void clear();
}
