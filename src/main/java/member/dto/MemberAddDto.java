package member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberAddDto {
    private String loginId;
    private String loginPw;
    private String username;
    private String email;
    private String phone;
    private String nickname;
    private String birth;
    private String gender;
}
