package tripplan.repository;

import member.Member;
import tripplan.TripPlan;
import util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class PlanJdbcRepository implements PlanRepository {
    private static final PlanRepository planRepository = new PlanJdbcRepository();

    private final DBConnectionUtil dbConnectionUtil;

    private PlanJdbcRepository() {
        dbConnectionUtil = DBConnectionUtil.getInstance();
    }

    public static PlanRepository getPlanRepository() {
        return planRepository;
    }

    @Override
    public int addTripPlan(Long memberId, String title) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "insert into trip_plan (member_id, title) values (? , ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, memberId);
            pstmt.setString(2, title);
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(pstmt, conn);
        }
        return count;
    }

    @Override
    public int addDetailPlan(Long tripPlanId, int contentId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "insert into detail_plan (trip_plan_id, content_id) values (? , ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, tripPlanId);
            pstmt.setInt(2, contentId);
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(pstmt, conn);
        }
        return count;
    }

    @Override
    public Optional<TripPlan> findById(Long tripPlanId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        TripPlan tripPlan = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "select * from trip_plan where trip_plan_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, tripPlanId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                tripPlan = createTripPlan(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(rs, pstmt, conn);
        }
        return Optional.ofNullable(tripPlan);
    }

    private TripPlan createTripPlan(ResultSet rs) throws SQLException {
        return TripPlan.builder()
                .id(rs.getLong("trip_plan_id"))
                .member(Member.builder()
                        .id(rs.getLong("member_id"))
                        .build()
                )
                .title(rs.getString("title"))
                .createdDate(rs.getTimestamp("created_date").toLocalDateTime())
                .lastModifiedDate(rs.getTimestamp("last_modified_date").toLocalDateTime())
                .build();
    }
}
