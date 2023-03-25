package common.validation.validator;

import common.validation.dto.InvalidResponse;
import member.dto.MemberAddDto;

import java.util.List;

public interface SignUpValidator {

    List<InvalidResponse> validate(MemberAddDto request);

}
