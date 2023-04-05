package member.repository;

import member.Member;
import member.dto.MemberAddDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static member.Authority.CLIENT;
import static org.assertj.core.api.Assertions.assertThat;

class MemberRepositoryTest {

    private final MemberRepository memberRepository = MemberJdbcRepository.getMemberRepository();

    @BeforeEach
    void beforeEach() {
        MemberAddDto memberAddDto = new MemberAddDto("ssafy", "12345678", "김싸피", "ssafy@ssafy.com", "01012345678", "010101", "1", "광주5반", CLIENT);
        memberRepository.save(memberAddDto);
    }

    @AfterEach
    void afterEach() {
        memberRepository.clear();
    }

    @Test
    @DisplayName("회원저장")
    void save() {
        //given
        MemberAddDto memberAddDto = new MemberAddDto("ssafy", "12345678", "김싸피", "ssafy@ssafy.com", "01012345678", "010101", "1", "광주5반", CLIENT);

        //when
        int count = memberRepository.save(memberAddDto);

        //then
        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("PK로 조회")
    void findById() {
        //given
        Member member = memberRepository.findByLoginIdAndLoginPw("ssafy").get();

        //when
        Optional<Member> findMember = memberRepository.findById(member.getId());

        //then
        assertThat(findMember).isPresent();
    }

    @Test
    @DisplayName("로그인 아이디로 조회")
    void findByLoginId() {
        //given
        String loginId = "ssafy";

        //when
        Optional<Member> findMember = memberRepository.findByLoginIdAndLoginPw(loginId);

        //then
        assertThat(findMember).isPresent();
    }

    @Test
    @DisplayName("이메일로 조회")
    void findByEmail() {
        //given
        String email = "ssafy@ssafy.com";

        //when
        Optional<Member> findMember = memberRepository.findByEmail(email);

        //then
        assertThat(findMember).isPresent();
    }

    @Test
    @DisplayName("연락처로 조회")
    void findByPhone() {
        //given
        String phone = "01012345678";

        //when
        Optional<Member> findMember = memberRepository.findByPhone(phone);

        //then
        assertThat(findMember).isPresent();
    }

    @Test
    @DisplayName("닉네임으로 조회")
    void findByNickname() {
        //given
        String nickname = "광주5반";

        //when
        Optional<Member> findMember = memberRepository.findByNickname(nickname);

        //then
        assertThat(findMember).isPresent();
    }

    @Test
    @DisplayName("회원업데이트")
    void update() {
        //given
        Member member = memberRepository.findByLoginIdAndLoginPw("ssafy").get();
        member.changeNickname("SSAFY9기");
        //when
        int count = memberRepository.update(member.getId(), member);

        //then
        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("회원삭제")
    void remove() {
        //given
        Member member = memberRepository.findByLoginIdAndLoginPw("ssafy").get();

        //when
        int count = memberRepository.remove(member.getId());

        //then
        assertThat(count).isEqualTo(1);
    }
}