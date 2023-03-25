package common.validation.validator.singup;

import common.validation.ValidationMessage;
import common.validation.dto.InvalidResponse;
import common.validation.validator.SignUpValidator;
import member.dto.MemberAddDto;

import java.util.Collections;
import java.util.List;

public class LoginIdValidator implements SignUpValidator {

    private static final InvalidResponse INVALID_RESPONSE = new InvalidResponse("loginId", ValidationMessage.LOGIN_ID);
    private static final int MAX_LENGTH = 20;
    private static final int MIN_LENGTH = 5;

    @Override
    public List<InvalidResponse> validate(MemberAddDto request) {
        String loginId = request.getLoginId();

        if (loginId == null || isBlank(loginId)) {
            return Collections.singletonList(INVALID_RESPONSE);
        }

        if (isLength(loginId)) {
            return Collections.singletonList(INVALID_RESPONSE);
        }

        if (isLowercaseOrNumber(loginId)) {
            return Collections.emptyList();
        }

        return Collections.singletonList(INVALID_RESPONSE);
    }

    private boolean isBlank(String loginId) {
        return loginId.trim().isEmpty();
    }

    private boolean isLength(String loginId) {
        return !(MIN_LENGTH <= loginId.length() && loginId.length() <= MAX_LENGTH);
    }

    private boolean isLowercaseOrNumber(String loginId) {
        return loginId.matches("[a-z0-9]+");
    }
}
