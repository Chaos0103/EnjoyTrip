package tripplan.repository;

import tripplan.TripPlan;

import java.util.Optional;

public interface PlanRepository {
    int addTripPlan(Long memberId, String title);
    int addDetailPlan(Long tripPlanId, int contentId);
    Optional<TripPlan> findById(Long tripPlanId);
    void clear();
}

