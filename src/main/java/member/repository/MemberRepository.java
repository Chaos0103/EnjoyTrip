package member.repository;

import member.Member;
import member.dto.MemberAddDto;

import java.util.Optional;

public interface MemberRepository {

    int save(MemberAddDto memberAddDto);

    Optional<Member> findById(Long memberId);

    Optional<Member> findByLoginId(String loginId);

    Optional<Member> findByLoginIdAndLoginPw(String loginId, String loginPw);

    Optional<Member> findByEmail(String email);

    Optional<Member> findByPhone(String phone);

    Optional<Member> findByNickname(String nickname);

    int update(Long memberId, Member member);

    int remove(Long memberId);

    void clear();
}
