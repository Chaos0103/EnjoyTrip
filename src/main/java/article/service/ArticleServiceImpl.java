package article.service;


import article.Article;
import article.dto.ArticleDto;
import article.dto.ArticleSearch;
import article.repository.ArticleJdbcRepository;
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
import java.util.stream.Collectors;

import static common.exception.ExceptionMessage.*;

public class ArticleServiceImpl implements ArticleService {

    private static final ArticleService articleService = new ArticleServiceImpl();
    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;

    public ArticleServiceImpl() {
        articleRepository = ArticleJdbcRepository.getArticleRepository();
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

        ArticleValidation articleValidation = new ArticleValidation();

        ArticleRequest request = ArticleRequest.builder()
                .title(articleDto.getTitle())
                .content(articleDto.getContent())
                .build();

        List<InvalidResponse> validate = articleValidation.validate(request);

        if (!validate.isEmpty()) {
            throw new ArticleException(ARTICLE_EXCEPTION);
        }

        return articleRepository.save(memberId, articleDto);
    }

    @Override
    public ArticleDto searchArticle(Long articleId) {
        Optional<Article> findArticle = articleRepository.findById(articleId);
        if (!findArticle.isPresent()) {
            throw new ArticleException(ARTICLE_EXCEPTION);
        }
        Article article = findArticle.get();
        return ArticleDto.builder()
                .title(article.getTitle())
                .content(article.getContent())
                .hit(article.getHit())
                .createdDate(article.getCreatedDate())
                .build();
    }

    @Override
    public List<ArticleDto> searchArticles(ArticleSearch condition) {
        List<Article> findArticles = articleRepository.findByCondition(condition);
        return findArticles.stream()
                .map(article -> ArticleDto.builder()
                        .articleId(article.getArticleId())
                        .title(article.getTitle())
                        .content(article.getContent())
                        .hit(article.getHit())
                        .createdDate(article.getCreatedDate())
                        .build()
                )
                .collect(Collectors.toList());
    }

    @Override
    public int getTotalCount() {
        return articleRepository.findTotalCount();
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

        ArticleValidation articleValidation = new ArticleValidation();

        ArticleRequest request = ArticleRequest.builder()
                .title(articleDto.getTitle())
                .content(articleDto.getContent())
                .build();

        List<InvalidResponse> validate = articleValidation.validate(request);
        if (!validate.isEmpty()) {
            throw new ArticleException(ARTICLE_EXCEPTION);
        }

        return articleRepository.update(articleId, articleDto);
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
