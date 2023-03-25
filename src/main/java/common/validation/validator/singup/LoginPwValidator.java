package common.validation.validator.singup;

import common.validation.ValidationMessage;
import common.validation.dto.InvalidResponse;
import common.validation.validator.SignUpValidator;
import member.dto.MemberAddDto;

import java.util.Collections;
import java.util.List;

public class LoginPwValidator implements SignUpValidator {

    private static final InvalidResponse INVALID_RESPONSE = new InvalidResponse("loginPw", ValidationMessage.LOGIN_PW);
    private static final int MAX_LENGTH = 20;
    private static final int MIN_LENGTH = 8;

    @Override
    public List<InvalidResponse> validate(MemberAddDto request) {
        String loginPw = request.getLoginPw();

        if (loginPw == null || isBlank(loginPw)) {
            return Collections.singletonList(INVALID_RESPONSE);
        }

        if (isLength(loginPw)) {
            return Collections.singletonList(INVALID_RESPONSE);
        }

        if (isEnglishOrNumber(loginPw)) {
            return Collections.emptyList();
        }

        return Collections.singletonList(INVALID_RESPONSE);
    }

    private boolean isBlank(String loginPw) {
        return loginPw.trim().isEmpty();
    }

    private boolean isLength(String loginPw) {
        return !(MIN_LENGTH <= loginPw.length() && loginPw.length() <= MAX_LENGTH);
    }

    private boolean isEnglishOrNumber(String loginPw) {
        return loginPw.matches("[a-zA-Z0-9]+");
    }
}
