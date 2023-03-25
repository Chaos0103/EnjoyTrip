package article.service;


import article.dto.ArticleDto;
import article.repository.ArticleJdbcRepository;
import article.repository.ArticleRepository;

public class ArticleServiceImpl implements ArticleService {

    private static final ArticleService articleService = new ArticleServiceImpl();
    private final ArticleRepository articleRepository;

    public ArticleServiceImpl() {
        articleRepository = ArticleJdbcRepository.getArticleRepository();
    }

    public static ArticleService getArticleService() {
        return articleService;
    }

    @Override
    public void addArticle(Long memberId, ArticleDto articleDto) {

    }
}
