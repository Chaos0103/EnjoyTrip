package notion.service;

import common.exception.NotionException;
import member.dto.MemberAddDto;
import member.repository.MemberJdbcRepository;
import member.repository.MemberRepository;
import notion.dto.NotionDto;
import notion.repository.NotionJdbcRepository;
import notion.repository.NotionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static member.Authority.ADMIN;
import static member.Authority.CLIENT;
import static org.assertj.core.api.Assertions.*;

class NotionServiceTest {

    private final NotionService notionService = NotionServiceImpl.getNotionService();
    private final NotionRepository notionRepository = NotionJdbcRepository.getNotionRepository();
    private final MemberRepository memberRepository = MemberJdbcRepository.getMemberRepository();

    private Long adminId;
    private Long clientId;
    private Long notionId;

    @BeforeEach
    void beforeEach() {
        memberRepository.save(new MemberAddDto("admin", "12345678", "김싸피", "ssafy@ssafy.com", "01012345678", "010101", "1", "광주5반", ADMIN));
        adminId = memberRepository.findByLoginId("admin").get().getId();

        memberRepository.save(new MemberAddDto("client", "12345678", "김싸피", "ssafy@ssafy.com", "01012345678", "010101", "1", "광주5반", CLIENT));
        clientId = memberRepository.findByLoginId("client").get().getId();

        notionRepository.save(adminId, new NotionDto("notion title", "notion content", false));
        notionId = notionRepository.findAll().get(0).getNotionId();
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

    @Test
    @DisplayName("공지사항 수정")
    void editNotion() {
        //given
        NotionDto notionDto = NotionDto.builder()
                .title("new notion title")
                .content("new notion content")
                .build();

        //when
        int count = notionService.editNotion(notionId, adminId, notionDto);

        //then
        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("공지사항 수정#미등록 회원")
    void editNotion_exception_member() {
        //given
        NotionDto notionDto = NotionDto.builder()
                .title("new notion title")
                .content("new notion content")
                .build();

        //when
        //then
        assertThatThrownBy(() -> notionService.editNotion(notionId, 0L, notionDto))
                .isInstanceOf(NotionException.class);
    }

    @Test
    @DisplayName("공지사항 수정#관리자 권한")
    void editNotion_exception_admin() {
        //given
        NotionDto notionDto = NotionDto.builder()
                .title("new notion title")
                .content("new notion content")
                .build();

        //when
        //then
        assertThatThrownBy(() -> notionService.editNotion(notionId, clientId, notionDto))
                .isInstanceOf(NotionException.class);
    }
    
    @Test
    @DisplayName("공지사항 수정#미등록 공지사항")
    void editNotion_exception_notion() {
        //given
        NotionDto notionDto = NotionDto.builder()
                .title("new notion title")
                .content("new notion content")
                .build();

        //when
        //then
        assertThatThrownBy(() -> notionService.editNotion(0L, adminId, notionDto))
                .isInstanceOf(NotionException.class);
    }

    @Test
    @DisplayName("공지사항 삭제")
    void removeNotion() {
        //given
        //when
        int count = notionService.removeNotion(notionId, adminId);

        //then
        assertThat(count).isEqualTo(1);
    }
    
    @Test
    @DisplayName("공지사항 삭제#미등록 회원")
    void removeNotion_exception_member() {
        //given
        //when
        //then
        assertThatThrownBy(() -> notionService.removeNotion(notionId, 0L))
                .isInstanceOf(NotionException.class);
    }
    
    @Test
    @DisplayName("공지사항 삭제#관리자 권한")
    void removeNotion_exception_admin() {
        //given
        //when
        //then
        assertThatThrownBy(() -> notionService.removeNotion(notionId, clientId))
                .isInstanceOf(NotionException.class);
    }

    @Test
    @DisplayName("공지사항 삭제#미등록 공지사항")
    void removeNotion_exception_notion() {
        //given
        //when
        //then
        assertThatThrownBy(() -> notionService.removeNotion(0L, clientId))
                .isInstanceOf(NotionException.class);
    }
}