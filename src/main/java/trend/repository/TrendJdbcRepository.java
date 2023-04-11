package trend.repository;

import trend.Trend;
import util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TrendJdbcRepository implements TrendRepository {

    private static final TrendRepository trendRepository = new TrendJdbcRepository();
    private final DBConnectionUtil dbConnectionUtil;

    private TrendJdbcRepository() {
        dbConnectionUtil = DBConnectionUtil.getInstance();
    }

    public static TrendRepository getTrendRepository() {
        return trendRepository;
    }

    @Override
    public int save(int contentId) {
        int count = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "insert into trend(content_id) values(?)";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, contentId);

            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(pstmt, conn);
        }
        return count;
    }

    @Override
    public int update(Trend trend) {
        int count = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "update trend" +
                    " set teenage=?, twenty=?, thirty=?, male=?, female=?" +
                    " where content_id=?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, trend.getTeenage());
            pstmt.setInt(2, trend.getTwenty());
            pstmt.setInt(3, trend.getThirty());
            pstmt.setInt(4, trend.getMale());
            pstmt.setInt(5, trend.getFemale());
            pstmt.setLong(6, trend.getContent().getId());

            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(pstmt, conn);
        }
        return count;
    }
}
