package notion.repository;

import notion.dto.NotionDto;
import util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NotionJdbcRepository implements NotionRepository {

    private static final NotionRepository notionRepository = new NotionJdbcRepository();
    private final DBConnectionUtil dbConnectionUtil;

    private NotionJdbcRepository() {
        dbConnectionUtil = DBConnectionUtil.getInstance();
    }

    public static NotionRepository getNotionRepository() {
        return notionRepository;
    }

    @Override
    public int save(Long memberId, NotionDto notionDto) {
        int count = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "insert into notion(title, content, top, created_by, last_modified_by) values (?, ?, ?, ?, ?);";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, notionDto.getTitle());
            pstmt.setString(2, notionDto.getContent());
            pstmt.setBoolean(3, notionDto.isTop());
            pstmt.setLong(4, memberId);
            pstmt.setLong(5, memberId);

            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(pstmt, conn);
        }
        return count;
    }

    @Override
    public void clear() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "delete from notion;";

            pstmt = conn.prepareStatement(sql);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(pstmt, conn);
        }
    }
}
