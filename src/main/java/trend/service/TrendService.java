package trend.service;

import trend.dto.TrendViewDto;

public interface TrendService {

    int increaseInfo(Long memberId, Long hotPlaceId);

    TrendViewDto popularByTeenage();

    TrendViewDto popularByTwenty();

    TrendViewDto popularByThirty();

    TrendViewDto popularByMale();

    TrendViewDto popularByFemale();
}
