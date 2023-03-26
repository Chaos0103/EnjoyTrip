package notion.repository;

import member.dto.MemberAddDto;
import member.repository.MemberJdbcRepository;
import member.repository.MemberRepository;
import notion.dto.NotionDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static member.Authority.ADMIN;
import static org.assertj.core.api.Assertions.*;

class NotionRepositoryTest {

    private final NotionRepository notionRepository = NotionJdbcRepository.getNotionRepository();
    private final MemberRepository memberRepository = MemberJdbcRepository.getMemberRepository();

    private Long memberId;

    @BeforeEach
    void beforeEach() {
        MemberAddDto memberAddDto = new MemberAddDto("admin", "12345678", "김싸피", "ssafy@ssafy.com", "01012345678", "010101", "1", "광주5반", ADMIN);
        memberRepository.save(memberAddDto);
        memberId = memberRepository.findByLoginId("admin").get().getMemberId();
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
        int count = notionRepository.save(memberId, notionDto);

        //then
        assertThat(count).isEqualTo(1);
    }
}