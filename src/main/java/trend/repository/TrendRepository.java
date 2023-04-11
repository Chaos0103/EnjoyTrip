package trend.repository;

import trend.Trend;

public interface TrendRepository {

    int save(int contentId);

    int update(Trend trend);
}
