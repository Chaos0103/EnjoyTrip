package member;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Entity
 * PK: memberId
 */
@Getter
@AllArgsConstructor
public class Member {

    private Long memberId;
    private String loginId;
    private String loginPw;
    private String username;
    private String email;
    private String phone;
    private String nickname;
    private String birth;
    private String gender;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    //== 비즈니스 로직 ==//
    public void changeLoginPw(String loginPw) {
        this.loginPw = loginPw;
    }

    public void changeEmail(String email) {
        this.email = email;
    }

    public void changePhone(String phone) {
        this.phone = phone;
    }

    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }
}
