package member.service;

import member.Member;
import member.dto.LoginMember;
import member.repository.MemberJdbcRepository;
import member.repository.MemberRepository;

import java.util.Optional;

public class AccountServiceImpl implements AccountService {

    private static final AccountService accountService = new AccountServiceImpl();
    private final MemberRepository memberRepository;

    private AccountServiceImpl() {
        memberRepository = MemberJdbcRepository.getMemberRepository();
    }

    public static AccountService getAccountService() {
        return accountService;
    }

    @Override
    public LoginMember login(String loginId, String password) {
        Optional<Member> findMember = memberRepository.findByLoginId(loginId);
        if (!findMember.isPresent()) {
            throw new IllegalArgumentException();
        }

        Member member = findMember.get();
        if (!member.getLoginPw().equals(password)) {
            throw new IllegalArgumentException();
        }

        return LoginMember.builder()
                .loginId(member.getLoginId())
                .loginPw(member.getLoginPw())
                .build();
    }
}
