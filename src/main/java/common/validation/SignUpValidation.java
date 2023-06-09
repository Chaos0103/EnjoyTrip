package common.validation;

import common.validation.dto.InvalidResponse;
import common.validation.dto.MemberRequest;
import common.validation.validator.MemberValidator;
import common.validation.validator.member.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SignUpValidation {
    private final List<MemberValidator> validators;

    public SignUpValidation() {
        validators = new ArrayList<>();
        validators.add(new LoginIdValidator());
        validators.add(new LoginPwValidator());
        validators.add(new UsernameValidator());
        validators.add(new EmailValidator());
        validators.add(new PhoneValidator());
        validators.add(new NicknameValidator());
        validators.add(new BirthValidator());
        validators.add(new GenderValidator());
    }

    public List<InvalidResponse> validate(MemberRequest request) {
        if (request == null) {
            return Collections.singletonList(new InvalidResponse("request", "유효하지 않은 요청"));
        }

        return validators.stream()
                .map(validator -> validator.validate(request))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
