package notion.service;

import common.exception.NotionException;
import member.dto.MemberAddDto;
import member.repository.MemberJdbcRepository;
import member.repository.MemberRepository;
import notion.dto.NotionDto;
import notion.repository.NotionJdbcRepository;
import notion.repository.NotionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static member.Authority.ADMIN;
import static member.Authority.CLIENT;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class NotionServiceTest {

    private final NotionService notionService = NotionServiceImpl.getNotionService();
    private final NotionRepository notionRepository = NotionJdbcRepository.getNotionRepository();
    private final MemberRepository memberRepository = MemberJdbcRepository.getMemberRepository();

    private Long adminId;
    private Long clientId;

    @BeforeEach
    void beforeEach() {
        memberRepository.save(new MemberAddDto("admin", "12345678", "김싸피", "ssafy@ssafy.com", "01012345678", "010101", "1", "광주5반", ADMIN));
        adminId = memberRepository.findByLoginId("admin").get().getMemberId();

        memberRepository.save(new MemberAddDto("client", "12345678", "김싸피", "ssafy@ssafy.com", "01012345678", "010101", "1", "광주5반", CLIENT));
        clientId = memberRepository.findByLoginId("client").get().getMemberId();
    }

    @AfterEach
    void afterEach() {
        notionRepository.clear();
        memberRepository.clear();
    }

    @Test
    @DisplayName("공지사항 등록")
    void addNotion() {
        //given
        NotionDto notionDto = NotionDto.builder()
                .title("notion title")
                .content("notion content")
                .top(true)
                .build();

        //when
        int count = notionService.addNotion(adminId, notionDto);

        //then
        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("공지사항 등록#미등록 회원")
    void addNotion_exception_member() {
        //given
        NotionDto notionDto = NotionDto.builder()
                .title("notion title")
                .content("notion content")
                .top(true)
                .build();

        //when
        //then
        assertThatThrownBy(() -> notionService.addNotion(0L, notionDto))
                .isInstanceOf(NotionException.class);
    }

    @Test
    @DisplayName("공지사항 등록#관리자 권한")
    void addNotion_exception_admin() {
        //given
        NotionDto notionDto = NotionDto.builder()
                .title("notion title")
                .content("notion content")
                .top(true)
                .build();

        //when
        //then
        assertThatThrownBy(() -> notionService.addNotion(clientId, notionDto))
                .isInstanceOf(NotionException.class);
    }
}