package member.service;

import common.exception.SignUpException;
import member.Member;
import member.dto.MemberAddDto;
import member.repository.MemberJdbcRepository;
import member.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static member.Authority.CLIENT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemberServiceTest {

    private final MemberService memberService = MemberServiceImpl.getMemberService();
    private final MemberRepository memberRepository = MemberJdbcRepository.getMemberRepository();

    @BeforeEach
    void beforeEach() {
        memberRepository.save(new MemberAddDto("ssafy", "12345678", "김싸피", "ssafy@ssafy.com", "01012345678", "010101", "1", "광주5반", CLIENT));
    }

    @AfterEach
    void afterEach() {
        memberRepository.clear();
    }

    @Test
    @DisplayName("회원가입")
    void signUp() {
        //given
        MemberAddDto memberAddDto = new MemberAddDto("ssafy9", "12345678", "김싸피", "ssafy9@ssafy.com", "01011111111",  "010101", "1", "싸피9기광주5반", CLIENT);

        //when
        memberService.signUp(memberAddDto);

        //then
        Optional<Member> findMember = memberRepository.findByLoginId("ssafy");
        assertThat(findMember).isPresent();
    }

    @Test
    @DisplayName("아이디 중복 예외")
    void exception_loginId() {
        //given
        MemberAddDto memberAddDto = new MemberAddDto("ssafy", "12345678", "김싸피", "ssafy@ssafy.com", "01011111111", "010101", "1", "싸피9기광주5반", CLIENT);

        //when
        //then
        assertThatThrownBy(() -> memberService.signUp(memberAddDto))
                .isInstanceOf(SignUpException.class);
    }

    @Test
    @DisplayName("이메일 중복 예외")
    void exception_email() {
        //given
        MemberAddDto memberAddDto = new MemberAddDto("ssafy9", "12345678", "김싸피", "ssafy@ssafy.com", "01011111111", "010101", "1", "싸피9기광주5반", CLIENT);


        //when
        //then
        assertThatThrownBy(() -> memberService.signUp(memberAddDto))
                .isInstanceOf(SignUpException.class);
    }

    @Test
    @DisplayName("연락처 중복 예외")
    void exception_phone() {
        //given
        MemberAddDto memberAddDto = new MemberAddDto("ssafy9", "12345678", "김싸피", "ssafy@ssafy.com", "01012345678", "010101", "1", "싸피9기광주5반", CLIENT);

        //when
        //then
        assertThatThrownBy(() -> memberService.signUp(memberAddDto))
                .isInstanceOf(SignUpException.class);
    }

    @Test
    @DisplayName("닉네임 중복 예외")
    void exception_nickname() {
        //given
        MemberAddDto memberAddDto = new MemberAddDto("ssafy9", "12345678", "김싸피", "ssafy@ssafy.com", "01011111111", "010101", "1", "광주5반", CLIENT);

        //when
        //then
        assertThatThrownBy(() -> memberService.signUp(memberAddDto))
                .isInstanceOf(SignUpException.class);
    }
}