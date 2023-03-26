package article.service;

import article.Article;
import article.dto.ArticleDto;
import article.repository.ArticleJdbcRepository;
import article.repository.ArticleRepository;
import common.exception.ArticleException;
import member.Member;
import member.dto.MemberAddDto;
import member.repository.MemberJdbcRepository;
import member.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static common.exception.ExceptionMessage.*;
import static member.Authority.CLIENT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ArticleServiceTest {

    private final ArticleService articleService = ArticleServiceImpl.getArticleService();

    private final ArticleRepository articleRepository = ArticleJdbcRepository.getArticleRepository();
    private final MemberRepository memberRepository = MemberJdbcRepository.getMemberRepository();

    private Long memberId;
    private Long articleId;

    @BeforeEach
    void beforeEach() {
        memberRepository.save(new MemberAddDto("ssafy", "12345678", "김싸피", "ssafy@ssafy.com", "01012345678", "010101", "1", "광주5반", CLIENT));
        memberId = memberRepository.findByLoginId("ssafy").get().getMemberId();
        articleRepository.save(memberId , new ArticleDto("beforeEach title", "beforeEach content"));
        articleId = articleRepository.findByMemberId(memberId).get(0).getArticleId();
    }

    @AfterEach
    void afterEach() {
        articleRepository.clear();
        memberRepository.clear();
    }

    @Test
    @DisplayName("게시물 등록")
    void addArticle() {
        //given
        ArticleDto articleDto = new ArticleDto("title", "content");

        //when
        int count = articleService.addArticle(memberId, articleDto);

        //then
        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("게시물 등록#미등록 회원 예외")
    void exception_member() {
        //given
        ArticleDto articleDto = new ArticleDto("title", "content");

        //when
        //then
        assertThatThrownBy(() -> articleService.addArticle(0L, articleDto))
                .isInstanceOf(ArticleException.class)
                .hasMessageContaining(ARTICLE_EXCEPTION);
    }

    @Test
    @DisplayName("게시물 수정")
    void editArticle() {
        //given
        ArticleDto articleDto = new ArticleDto("new title", "new content");

        //when
        int count = articleService.editArticle(articleId, memberId, articleDto);

        //then
        Article findArticle = articleRepository.findById(articleId).get();
        assertThat(findArticle.getTitle()).isEqualTo("new title");
    }

    @Test
    @DisplayName("게시물 수정#수정 권한이 없는 회원")
    void editArticle_exception_member() {
        //given
        ArticleDto articleDto = new ArticleDto("new title", "new content");

        //when
        //then
        assertThatThrownBy(() -> articleService.editArticle(articleId, 0L, articleDto))
                .isInstanceOf(ArticleException.class)
                .hasMessageContaining(ARTICLE_MEMBER_DISCREPANCY);
    }

    @Test
    @DisplayName("게시물 수정#등록되지 않은 게시물")
    void editArticle_exception_article() {
        //given
        ArticleDto articleDto = new ArticleDto("new title", "new content");

        //when
        //then
        assertThatThrownBy(() -> articleService.editArticle(0L, memberId, articleDto))
                .isInstanceOf(ArticleException.class)
                .hasMessageContaining(NOT_FOUND_ARTICLE);
    }
}