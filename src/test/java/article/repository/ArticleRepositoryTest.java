package article.repository;

import article.Article;
import article.dto.ArticleDto;
import member.Member;
import member.repository.MemberJdbcRepository;
import member.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static member.Authority.CLIENT;
import static org.assertj.core.api.Assertions.assertThat;

class ArticleRepositoryTest {

    private final ArticleRepository articleRepository = ArticleJdbcRepository.getArticleRepository();
    private final MemberRepository memberRepository = MemberJdbcRepository.getMemberRepository();

    @BeforeEach
    void beforeEach() {
        memberRepository.save(Member.builder()
                .loginId("ssafy")
                .loginPw("12345678")
                .username("김싸피")
                .email("ssafy@ssafy.com")
                .phone("01012345678")
                .birth("010101")
                .gender("1")
                .nickname("광주5반")
                .authority(CLIENT)
                .build());
    }

    @AfterEach
    void afterEach() {
        articleRepository.clear();
        memberRepository.clear();
    }

    @Test
    @DisplayName("게시글 저장")
    void save() {
        //given
        Member member = memberRepository.findByLoginId("ssafy").get();
        Article article = Article.builder().title("beforeEach title").content("beforeEach content").member(member).build();

        //when
        int count = articleRepository.save(article);

        //then
        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("게시글 수정")
    void update() {
        //given
        Member member = memberRepository.findByLoginId("ssafy").get();
        articleRepository.save(Article.builder().title("beforeEach title").content("beforeEach content").member(member).build());
        List<Article> articles = articleRepository.findAll();
        Article article = articles.get(0);
        article.editArticle("new title", "new content");

        //when
        int count = articleRepository.update(article);

        //then
        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("게시글 삭제")
    void remove() {
        //given
        Member member = memberRepository.findByLoginId("ssafy").get();
        articleRepository.save(Article.builder().title("beforeEach title").content("beforeEach content").member(member).build());
        List<Article> articles = articleRepository.findAll();
        Article article = articles.get(0);

        //when
        int count = articleRepository.remove(article.getId());

        //then
        assertThat(count).isEqualTo(1);
    }
}