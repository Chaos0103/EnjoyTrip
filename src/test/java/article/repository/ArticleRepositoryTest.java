package article.repository;

import article.dto.ArticleDto;
import member.Member;
import member.dto.MemberAddDto;
import member.repository.MemberJdbcRepository;
import member.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ArticleRepositoryTest {

    private final ArticleRepository articleRepository = ArticleJdbcRepository.getArticleRepository();
    private final MemberRepository memberRepository = MemberJdbcRepository.getMemberRepository();

    @BeforeEach
    void beforeEach() {
        MemberAddDto memberAddDto = new MemberAddDto("ssafy", "12345678", "김싸피", "ssafy@ssafy.com", "01012345678", "광주5반", "010101", "1");
        memberRepository.save(memberAddDto);
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
        ArticleDto articleDto = new ArticleDto("title", "content");

        //when
        int count = articleRepository.save(member.getMemberId(), articleDto);

        //then
        assertThat(count).isEqualTo(1);
    }
}