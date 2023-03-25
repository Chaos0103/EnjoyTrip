package member.repository;

import member.Member;
import member.dto.MemberAddDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {

    private MemberRepository memberRepository = MemberJdbcRepository.getMemberRepository();

    @AfterEach
    void afterEach() {
        memberRepository.clear();
    }

    @Test
    @DisplayName("회원저장")
    void create() {
        //given
        MemberAddDto memberAddDto = new MemberAddDto("ssafy", "1234", "김싸피", "ssafy@ssafy.com", "01012345678", "광주5반", "010101", "1");

        //when
        memberRepository.save(memberAddDto);

        //then
        Optional<Member> findMember = memberRepository.findByLoginId("ssafy");
        assertThat(findMember).isPresent();
    }
}