package member.repository;

import member.Member;

import java.util.Optional;

public interface LoginRepository {

    int save();

    Optional<Member> findById();

    int remove();
}
