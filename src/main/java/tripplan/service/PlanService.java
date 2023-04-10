package tripplan.service;

import tripplan.dto.PlanDto;
import tripplan.dto.PlanSearch;

import java.util.List;

public interface PlanService {

    int addTripPlan(Long memberId, String title);

    int addDetailPlan(Long memberId, Long tripPlanId, int contentId);

    List<PlanDto> searchPlans(PlanSearch condition);

    int updateTripPlan(Long memberId, Long tripPlanId, String title);

    int removeTripPlan(Long memberId, Long tripPlanId);

    int removeDetailPlan(Long memberId, Long detailPlanId);
}
