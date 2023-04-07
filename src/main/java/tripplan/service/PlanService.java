package tripplan.service;

import tripplan.TripPlan;

public interface PlanService {
    int addTripPlan(Long memberId, String title);
    int addDetailPlan(Long memberId, Long tripPlanId, int contentId);
    int updateTripPlan(Long memberId, Long tripPlanId, String title);
}
