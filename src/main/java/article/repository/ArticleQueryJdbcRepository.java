package article.repository;

import article.Article;
import article.dto.ArticleDetailDto;
import article.dto.ArticleListDto;
import article.dto.ArticleSearch;
import util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArticleQueryJdbcRepository implements ArticleQueryRepository {

    private static final ArticleQueryRepository articleQueryRepository = new ArticleQueryJdbcRepository();
    private final DBConnectionUtil dbConnectionUtil;

    private ArticleQueryJdbcRepository() {
        dbConnectionUtil = DBConnectionUtil.getInstance();
    }

    public static ArticleQueryRepository getArticleQueryRepository() {
        return articleQueryRepository;
    }

    @Override
    public Optional<ArticleDetailDto> findDetailById(Long articleId) {
        ArticleDetailDto articleDetailDto = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "select a.article_id, a.title, a.content, a.created_date, m.member_id, m.nickname" +
                    " from article a" +
                    " join member m on a.member_id=m.member_id" +
                    " where a.article_id=?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, articleId);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                articleDetailDto = ArticleDetailDto.builder()
                        .articleId(rs.getLong("article_id"))
                        .title(rs.getString("title"))
                        .content(rs.getString("content"))
                        .createdDate(dateFormat(rs.getTimestamp("created_date").toLocalDateTime()))
                        .memberId(rs.getLong("member_id"))
                        .nickname(rs.getString("nickname"))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(rs, pstmt, conn);
        }

        return Optional.ofNullable(articleDetailDto);
    }

    @Override
    public List<ArticleListDto> findListByCondition(ArticleSearch condition, int pageNum, int amount) {
        List<ArticleListDto> articleListDtos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "select article_id, title, created_date from article order by created_date desc limit ?, ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, (pageNum - 1) * amount);
            pstmt.setInt(2, amount);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                ArticleListDto articleListDto = ArticleListDto.builder()
                        .articleId(rs.getLong("article_id"))
                        .title(rs.getString("title"))
                        .createdDate(dateFormat(rs.getTimestamp("created_date").toLocalDateTime()))
                        .build();
                articleListDtos.add(articleListDto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(rs, pstmt, conn);
        }

        return articleListDtos;
    }

    private String dateFormat(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
