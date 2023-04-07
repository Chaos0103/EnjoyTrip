package tripplan.repository;

import member.Member;
import member.dto.MemberAddDto;
import member.repository.MemberJdbcRepository;
import member.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tripplan.DetailPlan;
import tripplan.TripPlan;

import java.util.List;

import static member.Authority.CLIENT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PlanRepositoryTest {

    private final PlanRepository planRepository = PlanJdbcRepository.getPlanRepository();
    private final MemberRepository memberRepository = MemberJdbcRepository.getMemberRepository();
    private long memberId;

    @BeforeEach
    void beforeEach() {
        memberRepository.save(new MemberAddDto("ssafy", "12345678", "김싸피", "ssafy@ssafy.com", "01012345678", "010101", "1", "광주5반", CLIENT));
        Member member = memberRepository.findByLoginId("ssafy").get();
        memberId = member.getId();
    }

    @AfterEach
    void afterEach() {
        planRepository.clear();
        memberRepository.clear();
    }

    @Test
    @DisplayName("여행계획 저장")
    void addTripPlan() {
        //given

        //when
        int result = planRepository.addTripPlan(memberId, "abcde");

        //then
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("세부여행계획 저장")
    void addDetailPlan() {
        //given
        planRepository.addTripPlan(memberId, "abc");
        List<TripPlan> findTripPlans = planRepository.findAllByMemberId(memberId);
        TripPlan tripPlan = findTripPlans.get(0);

        //when
        int result = planRepository.addDetailPlan(tripPlan.getId(), 125405);

        //then
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("여행계획 업데이트")
    void updateTripPlan() {
        //given
        planRepository.addTripPlan(memberId, "abc");
        List<TripPlan> findTripPlans = planRepository.findAllByMemberId(memberId);
        TripPlan tripPlan = findTripPlans.get(0);

        //when
        tripPlan.changeTitle("new title");
        int result = planRepository.updateTripPlan(tripPlan.getId(), tripPlan);

        //then
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("상세여행계획 삭제")
    void removeDetailPlan() {
        //given
        planRepository.addTripPlan(memberId, "abc");
        List<TripPlan> findTripPlans = planRepository.findAllByMemberId(memberId);
        TripPlan tripPlan = findTripPlans.get(0);
        planRepository.addDetailPlan(tripPlan.getId(), 125405);
        DetailPlan findDetailPlan = planRepository.findAllByTripPlanId(tripPlan.getId()).get(0);

        //when
        int result = planRepository.removeDetailPlan(findDetailPlan.getId());

        //then
        assertThat(result).isEqualTo(1);
    }
}