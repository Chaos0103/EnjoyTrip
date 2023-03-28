package member.service;

import common.exception.InformationChangeException;
import common.exception.SignUpException;
import common.exception.WithdrawalException;
import common.validation.MemberUpdateValidation;
import common.validation.dto.InvalidResponse;
import common.validation.dto.MemberRequest;
import member.Member;
import member.dto.MemberAddDto;
import member.dto.MemberDto;
import member.repository.MemberJdbcRepository;
import member.repository.MemberRepository;

import java.time.LocalDateTime;
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
    public int signUp(MemberAddDto memberAddDto) {
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

        return memberRepository.save(memberAddDto);
    }

    @Override
    public Optional<MemberDto> myPage(Long memberId) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        if (!findMember.isPresent()) {
            return Optional.empty();
        }

        Member member = findMember.get();
        MemberDto memberDto = MemberDto.builder()
                .loginId(member.getLoginId())
                .loginPw(member.getLoginPw())
                .username(member.getUsername())
                .email(member.getEmail())
                .phone(member.getPhone())
                .birth(member.getBirth())
                .gender(member.getGender())
                .nickname(member.getNickname())
                .authority(member.getAuthority())
                .build();

        return Optional.of(memberDto);
    }

    @Override
    public void changePassword(Long memberId, String loginPw) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        if (!findMember.isPresent()) {
            throw new InformationChangeException();
        }

        Member member = findMember.get();
        member.changeLoginPw(loginPw);

        updateValidation(member);

        memberRepository.update(memberId, member);
    }

    @Override
    public void changeEmail(Long memberId, String email) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        if (!findMember.isPresent()) {
            throw new InformationChangeException();
        }

        Optional<Member> emailCheck = memberRepository.findByEmail(email);
        if (emailCheck.isPresent()) {
            throw new InformationChangeException();
        }

        Member member = findMember.get();
        member.changeEmail(email);

        updateValidation(member);

        memberRepository.update(memberId, member);
    }

    @Override
    public void changePhone(Long memberId, String phone) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        if (!findMember.isPresent()) {
            throw new InformationChangeException();
        }

        Optional<Member> phoneCheck = memberRepository.findByPhone(phone);
        if (phoneCheck.isPresent()) {
            throw new InformationChangeException();
        }

        Member member = findMember.get();
        member.changePhone(phone);

        updateValidation(member);

        memberRepository.update(memberId, member);
    }

    @Override
    public void changeNickname(Long memberId, String nickname) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        if (!findMember.isPresent()) {
            throw new InformationChangeException();
        }

        Optional<Member> nicknameCheck = memberRepository.findByNickname(nickname);
        if (nicknameCheck.isPresent()) {
            throw new InformationChangeException();
        }

        Member member = findMember.get();
        if (member.getNicknameLastModifiedDate().isAfter(LocalDateTime.now().minusDays(30))) {
            throw new InformationChangeException();
        }

        member.changeNickname(nickname);

        updateValidation(member);

        memberRepository.update(memberId, member);
    }

    private void updateValidation(Member member) {
        MemberUpdateValidation validation = new MemberUpdateValidation();

        MemberRequest request = MemberRequest.builder()
                .loginPw(member.getLoginPw())
                .email(member.getEmail())
                .phone(member.getPhone())
                .nickname(member.getNickname())
                .build();

        List<InvalidResponse> responses = validation.validate(request);
        if (!responses.isEmpty()) {
            throw new InformationChangeException();
        }
    }

    @Override
    public void withdrawal(Long memberId, String loginPw) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        if (!findMember.isPresent()) {
            throw new WithdrawalException();
        }

        Member member = findMember.get();
        if (!member.getLoginPw().equals(loginPw)) {
            throw new WithdrawalException();
        }

        memberRepository.remove(memberId);

    }
}
