package member.repository;

import member.Member;
import member.dto.MemberAddDto;

import java.util.Optional;

public interface MemberRepository {

    void save(MemberAddDto memberAddDto);

    Optional<Member> findById(Long memberId);

    Optional<Member> findByLoginId(String loginId);

    Optional<Member> findByEmail(String email);

    Optional<Member> findByPhone(String phone);

    Optional<Member> findByNickname(String nickname);

    void update(Long memberId, Member member);

    void clear();
}
