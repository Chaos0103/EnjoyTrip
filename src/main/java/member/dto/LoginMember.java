package member.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class LoginMember {

    private Long id;
    private String loginId;
    private String loginPw;

    @Builder
    public LoginMember(Long id, String loginId, String loginPw) {
        this.id = id;
        this.loginId = loginId;
        this.loginPw = loginPw;
    }
}
