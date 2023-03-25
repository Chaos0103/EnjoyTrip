package article.repository;

import article.dto.ArticleDto;

public interface ArticleRepository {

    void save(ArticleDto articleDto);

}
