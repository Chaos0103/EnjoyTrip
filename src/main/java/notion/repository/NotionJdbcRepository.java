package notion.repository;

import member.Member;
import notion.Notion;
import notion.dto.NotionDto;
import util.DBConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Optional<Notion> findById(Long notionId) {
        Notion notion = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "select * from notion where notion_id=?;";

            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, notionId);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                notion = createNotion(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(rs, pstmt, conn);
        }
        return Optional.ofNullable(notion);
    }

    @Override
    public List<Notion> findAll() {
        List<Notion> notions = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "select * from notion";

            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                notions.add(createNotion(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(rs, pstmt, conn);
        }
        return notions;
    }

    @Override
    public int update(Long notionId, Notion notion) {
        int count = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "update notion set title=?, content=?, last_modified_by=?, last_modified_date=? where notion_id=?;";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, notion.getTitle());
            pstmt.setString(2, notion.getContent());
            pstmt.setLong(3, notion.getLastModifiedBy().getId());
            pstmt.setTimestamp(4, Timestamp.valueOf(notion.getLastModifiedDate()));
            pstmt.setLong(5, notionId);

            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(pstmt, conn);
        }
        return count;
    }

    @Override
    public int remove(Long notionId) {
        int count = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "delete from notion where notion_id=?;";

            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, notionId);

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

    private Notion createNotion(ResultSet rs) throws SQLException {
        return Notion.builder()
                .notionId(rs.getLong("notion_id"))
                .title(rs.getString("title"))
                .content(rs.getString("content"))
                .hit(rs.getInt("hit"))
                .top(rs.getBoolean("top"))
                .createdBy(new Member(rs.getLong("created_by")))
                .lastModifiedBy(new Member(rs.getLong("last_modified_by")))
                .createdDate(rs.getTimestamp("created_date").toLocalDateTime())
                .lastModifiedDate(rs.getTimestamp("last_modified_date").toLocalDateTime())
                .build();
    }
}
