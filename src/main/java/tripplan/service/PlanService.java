package tripplan.service;

import tripplan.dto.PlanDto;
import tripplan.dto.PlanListDto;
import tripplan.dto.PlanSearch;

import java.util.List;

public interface PlanService {

    int addTripPlan(Long memberId, String title);

    int addDetailPlan(Long memberId, Long tripPlanId, int contentId);


    List<PlanListDto> searchPlans(PlanSearch condition, int pageNum, int amount);

    int getTotalCount();

    int updateTripPlan(Long memberId, Long tripPlanId, String title);

    int removeTripPlan(Long memberId, Long tripPlanId);

    int removeDetailPlan(Long memberId, Long detailPlanId);
}
