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
            String sql = "insert into notion(member_id, title, content, top) values (?, ?, ?, ?);";

            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, memberId);
            pstmt.setString(2, notionDto.getTitle());
            pstmt.setString(3, notionDto.getContent());
            pstmt.setBoolean(4, notionDto.isTop());

            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(pstmt, conn);
        }
        return count;
    }
}
