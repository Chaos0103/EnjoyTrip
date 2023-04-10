package hotplace.repository;

import hotplace.HotPlace;
import hotplace.UploadFile;
import member.Member;
import member.dto.MemberAddDto;
import member.repository.MemberJdbcRepository;
import member.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static member.Authority.CLIENT;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class HotPlaceRepositoryTest {

    private final HotPlaceRepository hotPlaceRepository = HotPlaceJdbcRepository.getHotPlaceRepository();
    private final MemberRepository memberRepository = MemberJdbcRepository.getMemberRepository();
    private Long memberId;

    @BeforeEach
    void beforeEach() {
        memberRepository.save(new MemberAddDto("ssafy", "12345678", "김싸피", "ssafy@ssafy.com", "01012345678", "010101", "1", "광주5반", CLIENT));
        Member member = memberRepository.findByLoginId("ssafy").get();
        memberId = member.getId();
    }

    @AfterEach
    void afterEach() {
        hotPlaceRepository.clear();
        memberRepository.clear();
    }

    @Test
    @DisplayName("핫플레이스 저장")
    void save() {
        //given
        HotPlace hotPlace = HotPlace.builder()
                .name("핫플레이스 이름")
                .desc("핫플레이스 설명")
                .visitedDate("2020-01-01")
                .uploadFile(
                        UploadFile.builder()
                                .uploadFileName("uploadFileName.png")
                                .storeFileName("storeFileName.png")
                                .build()
                )
                .contentTypeId(11)
                .build();

        //when
        int result = hotPlaceRepository.save(memberId, 125405, hotPlace);

        //then
        assertThat(result).isEqualTo(1);
    }
}