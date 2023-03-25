package admin;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Admin {

    private Long adminId;
    private String loginId;
    private String loginPw;
    private String username;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
