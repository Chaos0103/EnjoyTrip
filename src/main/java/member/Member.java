package member;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Member {

    private Long memberId;
    private String loginId;
    private String loginPw;
    private String username;
    private String email;
    private String phone;
    private String birth;
    private String gender;
    private String nickname;
    private LocalDateTime nicknameLastModifiedDate;
    private Authority authority;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    public Member(Long memberId) {
        this.memberId = memberId;
    }

    @Builder
    public Member(Long memberId, String loginId, String loginPw, String username, String email, String phone, String birth, String gender, String nickname, LocalDateTime nicknameLastModifiedDate, Authority authority, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
        this.memberId = memberId;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.birth = birth;
        this.gender = gender;
        this.nickname = nickname;
        this.nicknameLastModifiedDate = nicknameLastModifiedDate;
        this.authority = authority;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    //== 비즈니스 로직 ==//
    public void changeLoginPw(String loginPw) {
        this.loginPw = loginPw;
        this.lastModifiedDate = LocalDateTime.now();
    }

    public void changeEmail(String email) {
        this.email = email;
        this.lastModifiedDate = LocalDateTime.now();
    }

    public void changePhone(String phone) {
        this.phone = phone;
        this.lastModifiedDate = LocalDateTime.now();
    }

    public void changeNickname(String nickname) {
        this.nickname = nickname;
        this.nicknameLastModifiedDate = LocalDateTime.now();
        this.lastModifiedDate = LocalDateTime.now();
    }
}
