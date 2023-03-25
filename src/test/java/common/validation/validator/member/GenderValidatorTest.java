package common.validation.validator.member;

import common.validation.dto.InvalidResponse;
import member.dto.MemberAddDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GenderValidatorTest {

    private final GenderValidator validator = new GenderValidator();

    @ParameterizedTest
    @DisplayName("성별 검증")
    @CsvSource({"1", "2", "3", "4"})
    void genderValidator(String gender) {
        //given
        MemberAddDto memberAddDto = MemberAddDto.builder()
                .gender(gender)
                .build();

        //when
        List<InvalidResponse> validate = validator.validate(memberAddDto);

        //then
        assertThat(validate).isEmpty();
    }

    @ParameterizedTest
    @DisplayName("성별 길이 예외")
    @CsvSource({"01", "22"})
    void exception_length(String gender) {
        //given
        MemberAddDto memberAddDto = MemberAddDto.builder()
                .gender(gender)
                .build();

        //when
        List<InvalidResponse> validate = validator.validate(memberAddDto);

        //then
        assertThat(validate).isNotEmpty();
    }

    @ParameterizedTest
    @DisplayName("성별 타입 예외")
    @CsvSource({"M", "!"})
    void exception_type(String gender) {
        //given
        MemberAddDto memberAddDto = MemberAddDto.builder()
                .gender(gender)
                .build();

        //when
        List<InvalidResponse> validate = validator.validate(memberAddDto);

        //then
        assertThat(validate).isNotEmpty();
    }

    @ParameterizedTest
    @DisplayName("성별 유효성 예외")
    @CsvSource({"5", "0"})
    void exception_check(String gender) {
        //given
        MemberAddDto memberAddDto = MemberAddDto.builder()
                .gender(gender)
                .build();

        //when
        List<InvalidResponse> validate = validator.validate(memberAddDto);

        //then
        assertThat(validate).isNotEmpty();
    }
}