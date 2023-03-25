package member.repository;

import member.Member;
import member.dto.MemberAddDto;

import java.util.Optional;

public interface MemberRepository {

    void save(MemberAddDto memberAddDto);

    Optional<Member> findById(Long memberId);

    Optional<Member> findByLoginId(String loginId);

    void clear();
}
