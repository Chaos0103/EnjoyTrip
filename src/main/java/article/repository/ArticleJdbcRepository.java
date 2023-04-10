package article.repository;

import article.Article;
import article.dto.ArticleSearch;
import member.Member;
import util.DBConnectionUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public int save(Article article) {
        int count = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "insert into article(member_id, title, content) values (?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, article.getMember().getId());
            pstmt.setString(2, article.getTitle());
            pstmt.setString(3, article.getContent());
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(pstmt, conn);
        }
        return count;
    }

    @Override
    public Optional<Article> findById(Long articleId) {
        Article article = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "select * from article where article_id = ?;";

            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, articleId);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                article = createArticle(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(rs, pstmt, conn);
        }
        return Optional.ofNullable(article);
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
    public int findTotalCount() {
        int result = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "select count(*) as total from article;";

            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(rs, pstmt, conn);
        }
        return result;
    }

    @Override
    public int update(Article article) {
        int count = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "update article set title=?, content=?, last_modified_date=? where article_id=?;";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, article.getTitle());
            pstmt.setString(2, article.getContent());
            pstmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            pstmt.setLong(4, article.getId());

            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(pstmt, conn);
        }
        return count;
    }

    @Override
    public int updateHit(Article article) {
        int count = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "update article set hit=? where article_id=?;";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, article.getHit());
            pstmt.setLong(1, article.getId());

            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(pstmt, conn);
        }
        return count;
    }

    @Override
    public int remove(Long articleId) {
        int count = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "delete from article where article_id = ?;";

            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, articleId);

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
        return new Article(articleId, title, content, hit, createdDate, lastModifiedDate, new Member(memberId));
    }

    private boolean hasText(String target) {
        return target == null || target.trim().isEmpty();
    }
}
