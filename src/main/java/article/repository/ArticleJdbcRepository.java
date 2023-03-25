package article.repository;

import article.Article;
import article.dto.ArticleDto;
import util.DBConnectionUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ArticleJdbcRepository implements ArticleRepository {

    private static final ArticleRepository articleRepository = new ArticleJdbcRepository();
    private final DBConnectionUtil dbConnectionUtil;

    private ArticleJdbcRepository() {
        dbConnectionUtil = DBConnectionUtil.getInstance();
    }

    public static ArticleRepository getArticleRepository() {
        return articleRepository;
    }

    @Override
    public int save(Long memberId, ArticleDto articleDto) {
        int count = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "insert into article(member_id, title, content) values (?, ?, ?);";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, memberId);
            pstmt.setString(2, articleDto.getTitle());
            pstmt.setString(3, articleDto.getContent());
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(pstmt, conn);
        }
        return count;
    }

    @Override
    public List<Article> findByMemberId(Long memberId) {
        List<Article> articles = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "select * from article where member_id = ?;";

            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, memberId);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                Article article = createArticle(rs);
                articles.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(rs, pstmt, conn);
        }
        return articles;
    }

    @Override
    public int update(Long articleId, ArticleDto articleDto) {
        int count = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "update article set title=?, content=?, last_modified_date=? where article_id=?;";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, articleDto.getTitle());
            pstmt.setString(2, articleDto.getContent());
            pstmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            pstmt.setLong(4, articleId);

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
            String sql = "delete from article;";

            pstmt = conn.prepareStatement(sql);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(pstmt, conn);
        }
    }

    private Article createArticle(ResultSet rs) throws SQLException {
        long articleId = rs.getLong("article_id");
        long memberId = rs.getLong("member_id");
        String title = rs.getString("title");
        String content = rs.getString("content");
        int hit = rs.getInt("hit");
        LocalDateTime createdDate = rs.getTimestamp("created_date").toLocalDateTime();
        LocalDateTime lastModifiedDate = rs.getTimestamp("last_modified_date").toLocalDateTime();
        return new Article(articleId, memberId, title, content, hit, createdDate, lastModifiedDate);
    }
}
