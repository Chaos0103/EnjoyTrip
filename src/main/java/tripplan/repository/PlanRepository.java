package tripplan.repository;

public interface PlanRepository {
    int addTripPlan(Long memberId, String title);
    int addDetailPlan(Long tripPlanId, int contentId);
}

