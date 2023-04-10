package article.service;

import article.Article;
import article.dto.ArticleDetailDto;
import article.dto.ArticleDto;
import article.dto.ArticleListDto;
import article.dto.ArticleSearch;
import article.repository.ArticleJdbcRepository;
import article.repository.ArticleQueryJdbcRepository;
import article.repository.ArticleQueryRepository;
import article.repository.ArticleRepository;
import common.exception.ArticleException;
import common.validation.ArticleValidation;
import common.validation.dto.ArticleRequest;
import common.validation.dto.InvalidResponse;
import member.Authority;
import member.Member;
import member.repository.MemberJdbcRepository;
import member.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

import static common.exception.ExceptionMessage.*;

public class ArticleServiceImpl implements ArticleService {

    private static final ArticleService articleService = new ArticleServiceImpl();
    private final ArticleRepository articleRepository;
    private final ArticleQueryRepository articleQueryRepository;
    private final MemberRepository memberRepository;

    public ArticleServiceImpl() {
        articleRepository = ArticleJdbcRepository.getArticleRepository();
        articleQueryRepository = ArticleQueryJdbcRepository.getArticleQueryRepository();
        memberRepository = MemberJdbcRepository.getMemberRepository();
    }

    public static ArticleService getArticleService() {
        return articleService;
    }

    @Override
    public int addArticle(Long memberId, ArticleDto articleDto) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        if (!findMember.isPresent()) {
            throw new ArticleException(ARTICLE_EXCEPTION);
        }

        Article article = Article.builder()
                .title(articleDto.getTitle())
                .content(articleDto.getContent())
                .member(findMember.get())
                .build();

        return articleRepository.save(article);
    }

    @Override
    public ArticleDetailDto searchArticle(Long articleId) {
        Optional<ArticleDetailDto> findArticle = articleQueryRepository.findDetailById(articleId);
        if (!findArticle.isPresent()) {
            throw new ArticleException(ARTICLE_EXCEPTION);
        }
        return findArticle.get();
    }

    @Override
    public List<ArticleListDto> searchArticles(ArticleSearch condition, int pageNum, int amount) {
        return articleQueryRepository.findListByCondition(condition, pageNum, amount);
    }

    @Override
    public int getTotalCount() {
        return articleQueryRepository.findTotalCount();
    }

    @Override
    public int editArticle(Long articleId, Long memberId, ArticleDto articleDto) {
        Optional<Article> findArticle = articleRepository.findById(articleId);
        if (!findArticle.isPresent()) {
            throw new ArticleException(NOT_FOUND_ARTICLE);
        }

        Article article = findArticle.get();
        if (!article.getMember().getId().equals(memberId)) {
            throw new ArticleException(ARTICLE_MEMBER_DISCREPANCY);
        }

        article.editArticle(articleDto.getTitle(), articleDto.getContent());

        return articleRepository.update(article);
    }

    @Override
    public int increaseHit(Long articleId) {
        Optional<Article> findArticle = articleRepository.findById(articleId);
        if (!findArticle.isPresent()) {
            throw new ArticleException(ARTICLE_EXCEPTION);
        }

        Article article = findArticle.get();
        article.increaseHit();

        return articleRepository.updateHit(article);
    }

    @Override
    public int removeArticle(Long articleId, Long memberId) {
        Optional<Article> findArticle = articleRepository.findById(articleId);
        if (!findArticle.isPresent()) {
            throw new ArticleException(NOT_FOUND_ARTICLE);
        }

        Optional<Member> findMember = memberRepository.findById(memberId);
        if (!findMember.isPresent()) {
            throw new ArticleException(ARTICLE_MEMBER_DISCREPANCY);
        }

        Article article = findArticle.get();
        Member member = findMember.get();
        if (!article.getMember().getId().equals(memberId) && member.getAuthority() == Authority.CLIENT) {
            throw new ArticleException(ARTICLE_MEMBER_DISCREPANCY);
        }

        return articleRepository.remove(articleId);
    }
}
