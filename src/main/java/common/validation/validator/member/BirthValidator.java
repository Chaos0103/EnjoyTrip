package common.validation.validator.member;

import common.validation.ValidationMessage;
import common.validation.dto.InvalidResponse;
import common.validation.validator.MemberValidator;
import member.dto.MemberAddDto;

import java.util.Collections;
import java.util.List;

public class BirthValidator implements MemberValidator {

    private static final InvalidResponse INVALID_RESPONSE = new InvalidResponse("birth", ValidationMessage.BIRTH);
    private static final int LENGTH = 6;

    @Override
    public List<InvalidResponse> validate(MemberAddDto request) {
        String birth = request.getBirth();

        if (birth == null || isBlank(birth)) {
            return Collections.singletonList(INVALID_RESPONSE);
        }

        if (isLength(birth)) {
            return Collections.singletonList(INVALID_RESPONSE);
        }

        if (isNumber(birth)) {
            return Collections.emptyList();
        }

        return Collections.singletonList(INVALID_RESPONSE);
    }

    private boolean isBlank(String birth) {
        return birth.trim().isEmpty();
    }

    private boolean isLength(String birth) {
        return birth.length() != LENGTH;
    }

    private boolean isNumber(String birth) {
        return birth.matches("[0-9]+");
    }
}
