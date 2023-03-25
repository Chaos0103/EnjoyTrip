package member.service;

import common.exception.LoginException;
import member.dto.LoginMember;
import member.dto.MemberAddDto;
import member.repository.MemberJdbcRepository;
import member.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static common.exception.ExceptionMessage.LOGIN_EXCEPTION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AccountServiceTest {

    private final AccountService accountService = AccountServiceImpl.getAccountService();
    private final MemberRepository memberRepository = MemberJdbcRepository.getMemberRepository();

    @BeforeEach
    void beforeEach() {
        MemberAddDto memberAddDto = new MemberAddDto("ssafy", "12345678", "김싸피", "ssafy@ssafy.com", "01012345678", "광주5반", "010101", "1");
        memberRepository.save(memberAddDto);
    }

    @AfterEach
    void afterEach() {
        memberRepository.clear();
    }

    @Test
    @DisplayName("로그인")
    void login() {
        //given
        String loginId = "ssafy";
        String loginPw = "12345678";

        //when
        LoginMember loginMember = accountService.login(loginId, loginPw);

        //then
        assertThat(loginMember.getLoginId()).isEqualTo(loginId);
        assertThat(loginMember.getLoginPw()).isEqualTo(loginPw);
    }

    @Test
    @DisplayName("로그인#아이디 오류")
    void login_exception_loginId() {
        //given
        String loginId = "ssafy1234";
        String loginPw = "12345678";

        //when
        //then
        assertThatThrownBy(() -> accountService.login(loginId, loginPw))
                .isInstanceOf(LoginException.class)
                .hasMessageContaining(LOGIN_EXCEPTION);
    }

    @Test
    @DisplayName("로그인#비밀번호 오류")
    void login_exception_loginPw() {
        //given
        String loginId = "ssafy";
        String loginPw = "87654321";

        //when
        //then
        assertThatThrownBy(() -> accountService.login(loginId, loginPw))
                .isInstanceOf(LoginException.class)
                .hasMessageContaining(LOGIN_EXCEPTION);
    }
}