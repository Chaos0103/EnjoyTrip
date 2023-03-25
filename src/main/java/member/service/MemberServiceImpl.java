package member.service;

import common.validation.SignUpValidation;
import common.validation.dto.InvalidResponse;
import common.exception.SignUpException;
import member.Member;
import member.dto.MemberAddDto;
import member.repository.MemberJdbcRepository;
import member.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberServiceImpl implements MemberService {

    private static final MemberService memberService = new MemberServiceImpl();
    private final MemberRepository memberRepository;

    private MemberServiceImpl() {
        memberRepository = MemberJdbcRepository.getMemberRepository();
    }

    public static MemberService getMemberService() {
        return memberService;
    }

    @Override
    public void signUp(MemberAddDto memberAddDto) {
        SignUpValidation validation = new SignUpValidation();

        List<InvalidResponse> responses = validation.validate(memberAddDto);

        if (!responses.isEmpty()) {
            throw new SignUpException();
        }

        Optional<Member> loginIdCheck = memberRepository.findByLoginId(memberAddDto.getLoginId());
        if (loginIdCheck.isPresent()) {
            throw new SignUpException();
        }

        Optional<Member> emailCheck = memberRepository.findByEmail(memberAddDto.getEmail());
        if (emailCheck.isPresent()) {
            throw new SignUpException();
        }

        Optional<Member> phoneCheck = memberRepository.findByPhone(memberAddDto.getPhone());
        if (phoneCheck.isPresent()) {
            throw new SignUpException();
        }

        Optional<Member> nicknameCheck = memberRepository.findByNickname(memberAddDto.getNickname());
        if (nicknameCheck.isPresent()) {
            throw new SignUpException();
        }

        memberRepository.save(memberAddDto);
    }
}
