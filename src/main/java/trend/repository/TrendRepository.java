package trend.repository;

import trend.Trend;

import java.util.Optional;

public interface TrendRepository {

    int save(int contentId);

    Optional<Trend> findByContentId(int contentId);

    int update(Trend trend);
}
