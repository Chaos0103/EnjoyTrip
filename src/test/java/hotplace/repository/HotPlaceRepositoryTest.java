package hotplace.repository;

import hotplace.HotPlace;
import hotplace.UploadFile;
import hotplace.dto.HotPlaceSearch;
import member.Member;
import member.dto.MemberAddDto;
import member.repository.MemberJdbcRepository;
import member.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static member.Authority.CLIENT;
import static org.assertj.core.api.Assertions.*;

class HotPlaceRepositoryTest {

    private final HotPlaceRepository hotPlaceRepository = HotPlaceJdbcRepository.getHotPlaceRepository();
    private final MemberRepository memberRepository = MemberJdbcRepository.getMemberRepository();
    private Long memberId;
    private Long hotPlaceId;

    @BeforeEach
    void beforeEach() {
        memberRepository.save(new MemberAddDto("ssafy", "12345678", "김싸피", "ssafy@ssafy.com", "01012345678", "010101", "1", "광주5반", CLIENT));
        Member member = memberRepository.findByLoginId("ssafy").get();
        memberId = member.getId();

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
        hotPlaceRepository.save(memberId, 125405, hotPlace);
        List<HotPlace> findHotPlace = hotPlaceRepository.findByCondition(new HotPlaceSearch("", 1));
        hotPlaceId = findHotPlace.get(0).getId();
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

    @Test
    @DisplayName("핫플레이스 업데이트")
    void update() {
        //given
        HotPlace hotPlace = hotPlaceRepository.findById(hotPlaceId).get();
        hotPlace.changeName("new hot place name");

        //when
        int result = hotPlaceRepository.update(hotPlaceId, hotPlace);

        //then
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("핫플레이스 삭제")
    void remove() {
        //given
        //when
        int result = hotPlaceRepository.remove(hotPlaceId);

        //then
        assertThat(result).isEqualTo(1);
    }
}