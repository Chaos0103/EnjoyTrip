package article.service;


import article.Article;
import article.dto.ArticleDto;
import article.repository.ArticleJdbcRepository;
import article.repository.ArticleRepository;
import common.exception.ArticleException;
import common.validation.ArticleValidation;
import common.validation.dto.ArticleRequest;
import common.validation.dto.InvalidResponse;
import member.Member;
import member.repository.MemberJdbcRepository;
import member.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

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
    public int editArticle(Long articleId, Long memberId, ArticleDto articleDto) {
        Optional<Article> findArticle = articleRepository.findById(articleId);
        if (!findArticle.isPresent()) {
            throw new ArticleException(NOT_FOUND_ARTICLE);
        }

        Article article = findArticle.get();
        if (!article.getMemberId().equals(memberId)) {
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
}
