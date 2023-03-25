package member.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class MemberAddDto {

    private String loginId;
    private String loginPw;
    private String username;
    private String email;
    private String phone;
    private String nickname;
    private String birth;
    private String gender;

    @Builder
    public MemberAddDto(String loginId, String loginPw, String username, String email, String phone, String nickname, String birth, String gender) {
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.nickname = nickname;
        this.birth = birth;
        this.gender = gender;
    }
}
