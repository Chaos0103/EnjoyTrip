package common.validation.validator.signup;

import common.validation.dto.InvalidResponse;
import common.validation.validator.signup.LoginIdValidator;
import member.dto.MemberAddDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LoginIdValidatorTest {

    private final LoginIdValidator validator = new LoginIdValidator();

    @Test
    @DisplayName("로그인 아이디 검증")
    void loginIdValidator() {
        //given
        MemberAddDto memberAddDto = MemberAddDto.builder()
                .loginId("ssafy")
                .build();

        //when
        List<InvalidResponse> validate = validator.validate(memberAddDto);

        //then
        assertThat(validate).isEmpty();
    }

    @ParameterizedTest
    @DisplayName("로그인 아이디 길이 예외")
    @CsvSource({"0123", "012345678901234567890", "null", "1234 "})
    void exception_length(String loginId) {
        //given
        MemberAddDto memberAddDto = MemberAddDto.builder()
                .loginId(loginId)
                .build();

        //when
        List<InvalidResponse> validate = validator.validate(memberAddDto);

        //then
        assertThat(validate).isNotEmpty();
    }

    @ParameterizedTest
    @DisplayName("로그인 아이디 타입 예외")
    @CsvSource({"ssafy!", "ssafy 9", "싸피", "SSAFY"})
    void exception_type(String loginId) {
        //given
        MemberAddDto memberAddDto = MemberAddDto.builder()
                .loginId(loginId)
                .build();

        //when
        List<InvalidResponse> validate = validator.validate(memberAddDto);

        //then
        assertThat(validate).isNotEmpty();
    }
}