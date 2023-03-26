package member.service;

import member.dto.MemberAddDto;

public interface MemberService {

    void signUpClient(MemberAddDto memberAddDto);

    void changePassword(Long memberId, String loginPw);

    void changeEmail(Long memberId, String email);

    void changePhone(Long memberId, String phone);

    void changeNickname(Long memberId, String nickname);

    void withdrawal(Long memberId, String loginPw);
}
