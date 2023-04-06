package tripplan.service;

import attraction.AttractionInfo;
import attraction.repository.AttractionJdbcRepository;
import attraction.repository.AttractionRepository;
import common.exception.PlanException;
import member.Member;
import member.repository.MemberJdbcRepository;
import member.repository.MemberRepository;
import tripplan.TripPlan;
import tripplan.repository.PlanJdbcRepository;
import tripplan.repository.PlanRepository;

import java.util.Optional;

public class PlanServiceImpl implements PlanService {
    private static final PlanService planService = new PlanServiceImpl();
    private final PlanRepository planRepository;
    private final MemberRepository memberRepository;
    private final AttractionRepository attractionRepository;

    private PlanServiceImpl() {
        planRepository = PlanJdbcRepository.getPlanRepository();
        memberRepository = MemberJdbcRepository.getMemberRepository();
        attractionRepository = AttractionJdbcRepository.getAttractionRepository();
    }

    public PlanService getPlanService() {
        return planService;
    }

    @Override
    public int addTripPlan(Long memberId, String title) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        if (!findMember.isPresent()) {
            throw new PlanException();
        }
        return planRepository.addTripPlan(memberId, title);
    }

    @Override
    public int addDetailPlan(Long memberId, Long tripPlanId, int contentId) {
        Optional<TripPlan> findTripPlan = planRepository.findById(tripPlanId);
        if (!findTripPlan.isPresent()) {
            throw new PlanException();
        }

        Optional<AttractionInfo> findAttractionInfo = attractionRepository.findById(contentId);
        if (!findAttractionInfo.isPresent()) {
            throw new PlanException();
        }

        TripPlan tripPlan = findTripPlan.get();
        if (!tripPlan.getMember().getId().equals(memberId)) {
            throw new PlanException();
        }

        return planRepository.addDetailPlan(tripPlanId, contentId);
    }
}
