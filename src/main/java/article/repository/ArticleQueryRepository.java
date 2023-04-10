package article.repository;

import article.dto.ArticleDetailDto;

import java.util.Optional;

public interface ArticleQueryRepository {

    Optional<ArticleDetailDto> findDetailById(Long articleId);

}
