package member.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class LoginMember {

    private String loginId;
    private String loginPw;

    @Builder
    public LoginMember(String loginId, String loginPw) {
        this.loginId = loginId;
        this.loginPw = loginPw;
    }
}
