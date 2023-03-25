package member.service;

import member.dto.MemberAddDto;

public interface MemberService {
    void signUp(MemberAddDto memberAddDto);
}
