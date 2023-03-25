package common.validation.validator.signup;

import common.validation.ValidationMessage;
import common.validation.dto.InvalidResponse;
import common.validation.validator.SignUpValidator;
import member.dto.MemberAddDto;

import java.util.Collections;
import java.util.List;

public class PhoneValidator implements SignUpValidator {

    private static final InvalidResponse INVALID_RESPONSE = new InvalidResponse("phone", ValidationMessage.PHONE);
    private static final int LENGTH = 11;

    @Override
    public List<InvalidResponse> validate(MemberAddDto request) {
        String phone = request.getPhone();

        if (phone == null || isBlank(phone)) {
            return Collections.singletonList(INVALID_RESPONSE);
        }

        if (isLength(phone)) {
            return Collections.singletonList(INVALID_RESPONSE);
        }

        if (isNumber(phone)) {
            return Collections.emptyList();
        }

        return Collections.singletonList(INVALID_RESPONSE);
    }

    private boolean isBlank(String phone) {
        return phone.trim().isEmpty();
    }

    private boolean isLength(String phone) {
        return phone.length() != LENGTH;
    }

    private boolean isNumber(String phone) {
        return phone.matches("[0-9]+");
    }
}
