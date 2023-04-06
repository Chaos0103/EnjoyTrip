package tripplan.service;

public interface PlanService {
    int addTripPlan(Long memberId, String title);
    int addDetailPlan(Long memberId, Long tripPlanId, int contentId);
}
