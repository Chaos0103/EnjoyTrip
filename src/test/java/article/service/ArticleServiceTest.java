package article.service;

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

import static common.exception.ExceptionMessage.ARTICLE_EXCEPTION;
import static member.Authority.CLIENT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ArticleServiceTest {

    private final ArticleService articleService = ArticleServiceImpl.getArticleService();

    private final ArticleRepository articleRepository = ArticleJdbcRepository.getArticleRepository();
    private final MemberRepository memberRepository = MemberJdbcRepository.getMemberRepository();

    @BeforeEach
    void beforeEach() {
        MemberAddDto memberAddDto = new MemberAddDto("ssafy", "12345678", "김싸피", "ssafy@ssafy.com", "01012345678", "010101", "1", "광주5반", CLIENT);
        memberRepository.save(memberAddDto);
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
        Member member = memberRepository.findByLoginId("ssafy").get();
        ArticleDto articleDto = new ArticleDto("title", "content");

        //when
        int count = articleService.addArticle(member.getMemberId(), articleDto);

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
}