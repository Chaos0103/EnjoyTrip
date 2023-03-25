package article.repository;

import article.Article;
import article.dto.ArticleDto;
import util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
    public void save(ArticleDto articleDto) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "insert into article(member_id, title, content) values (?, ?, ?);";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, articleDto.getMemberId());
            pstmt.setString(2, articleDto.getTitle());
            pstmt.setString(3, articleDto.getContent());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(pstmt, conn);
        }
    }
}
