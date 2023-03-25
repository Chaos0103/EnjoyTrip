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


}
