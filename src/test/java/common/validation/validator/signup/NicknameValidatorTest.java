package common.validation.validator.signup;

import common.validation.dto.InvalidResponse;
import member.dto.MemberAddDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class NicknameValidatorTest {

    private final NicknameValidator validator = new NicknameValidator();

    @Test
    @DisplayName("닉네임 검증")
    void nicknameValidator() {
        //given
        MemberAddDto memberAddDto = MemberAddDto.builder()
                .nickname("광주5반")
                .build();

        //when
        List<InvalidResponse> validate = validator.validate(memberAddDto);

        //then
        assertThat(validate).isEmpty();
    }

    @ParameterizedTest
    @DisplayName("닉네임 길이 예외")
    @CsvSource({"01234567890"})
    void exception_length(String nickname) {
        //given
        MemberAddDto memberAddDto = MemberAddDto.builder()
                .nickname(nickname)
                .build();

        //when
        List<InvalidResponse> validate = validator.validate(memberAddDto);

        //then
        assertThat(validate).isNotEmpty();
    }
}