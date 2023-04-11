package member;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Login {

    private String authenticationKey;
    private Member member;

    @Builder
    public Login(String authenticationKey, Member member) {
        this.authenticationKey = authenticationKey;
        this.member = member;
    }
}
