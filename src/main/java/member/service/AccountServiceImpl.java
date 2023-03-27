package member.service;

import common.exception.AccountException;
import common.exception.LoginException;
import member.Member;
import member.dto.LoginMember;
import member.repository.MemberJdbcRepository;
import member.repository.MemberRepository;

import java.util.Optional;

import static common.exception.ExceptionMessage.ACCOUNT_EXCEPTION;
import static common.exception.ExceptionMessage.LOGIN_EXCEPTION;

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
            throw new LoginException(LOGIN_EXCEPTION);
        }

        Member member = findMember.get();
        if (!member.getLoginPw().equals(password)) {
            throw new LoginException(LOGIN_EXCEPTION);
        }

        return LoginMember.builder()
                .id(member.getId())
                .loginId(member.getLoginId())
                .loginPw(member.getLoginPw())
                .build();
    }

    @Override
    public String findLoginId(String email, String phone) {
        Optional<Member> findMember = memberRepository.findByEmail(email);
        if (!findMember.isPresent()) {
            throw new AccountException(ACCOUNT_EXCEPTION);
        }

        Member member = findMember.get();
        if (!member.getPhone().equals(phone)) {
            throw new AccountException(ACCOUNT_EXCEPTION);
        }

        return member.getLoginId();
    }

    @Override
    public String findLoginPw(String loginId, String email, String phone) {
        Optional<Member> findMember = memberRepository.findByLoginId(loginId);
        if (!findMember.isPresent()) {
            throw new AccountException(ACCOUNT_EXCEPTION);
        }

        Member member = findMember.get();
        if (!member.getEmail().equals(email) || !member.getPhone().equals(phone)) {
            throw new AccountException(ACCOUNT_EXCEPTION);
        }

        return member.getLoginPw();
    }
}
