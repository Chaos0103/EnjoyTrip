package notion.repository;

import member.Member;
import member.dto.MemberAddDto;
import member.repository.MemberJdbcRepository;
import member.repository.MemberRepository;
import notion.Notion;
import notion.dto.NotionDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static member.Authority.ADMIN;
import static org.assertj.core.api.Assertions.*;

class NotionRepositoryTest {

    private final NotionRepository notionRepository = NotionJdbcRepository.getNotionRepository();
    private final MemberRepository memberRepository = MemberJdbcRepository.getMemberRepository();

    private Member member;

    @BeforeEach
    void beforeEach() {
        MemberAddDto memberAddDto = new MemberAddDto("admin", "12345678", "김싸피", "ssafy@ssafy.com", "01012345678", "010101", "1", "광주5반", ADMIN);
        memberRepository.save(memberAddDto);
        member = memberRepository.findByLoginIdAndLoginPw("admin").get();
    }

    @AfterEach
    void afterEach() {
        notionRepository.clear();
        memberRepository.clear();
    }

    @Test
    @DisplayName("공지사항 저장")
    void save() {
        //given
        NotionDto notionDto = NotionDto.builder()
                .title("notion title")
                .content("notion content")
                .build();

        //when
        int count = notionRepository.save(member.getId(), notionDto);

        //then
        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("공지사항 수정")
    void update() {
        //given
        notionRepository.save(member.getId(), new NotionDto(0L,"notion title", "notion content", false, null));
        Notion notion = notionRepository.findAll().get(0);
        notion.edit("new notion title", "new notion content", member);

        //when
        int count = notionRepository.update(notion.getId(), notion);

        //then
        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("공지사항 삭제")
    void remove() {
        //given
        notionRepository.save(member.getId(), new NotionDto(0L,"notion title", "notion content", false, null));
        Notion notion = notionRepository.findAll().get(0);

        //when
        int count = notionRepository.remove(notion.getId());

        //then
        assertThat(count).isEqualTo(1);
    }
}