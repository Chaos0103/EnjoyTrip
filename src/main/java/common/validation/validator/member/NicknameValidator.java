package common.validation.validator.member;

import common.validation.ValidationMessage;
import common.validation.dto.InvalidResponse;
import common.validation.validator.MemberValidator;
import member.dto.MemberAddDto;

import java.util.Collections;
import java.util.List;

public class NicknameValidator implements MemberValidator {

    private static final InvalidResponse INVALID_RESPONSE = new InvalidResponse("nickname", ValidationMessage.NICKNAME);
    private static final int LENGTH = 10;

    @Override
    public List<InvalidResponse> validate(MemberAddDto request) {
        String nickname = request.getNickname();

        if (nickname == null || isBlank(nickname)) {
            return Collections.singletonList(INVALID_RESPONSE);
        }

        if (isLength(nickname)) {
            return Collections.singletonList(INVALID_RESPONSE);
        }

        return Collections.emptyList();
    }

    private boolean isBlank(String nickname) {
        return nickname.trim().isEmpty();
    }

    private boolean isLength(String nickname) {
        return LENGTH < nickname.length();
    }
}
