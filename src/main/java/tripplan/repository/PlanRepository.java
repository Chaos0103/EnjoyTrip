package tripplan.repository;

import tripplan.DetailPlan;
import tripplan.TripPlan;

import java.util.List;
import java.util.Optional;

public interface PlanRepository {
    int addTripPlan(Long memberId, String title);

    int addDetailPlan(Long tripPlanId, int contentId);

    Optional<TripPlan> findById(Long tripPlanId);

    List<TripPlan> findAllByMemberId(Long memberId);

    int updateTripPlan(Long tripPlanId, TripPlan tripPlan);

    int removeDetailPlan(Long detailPlanId);

    void clear();
}

