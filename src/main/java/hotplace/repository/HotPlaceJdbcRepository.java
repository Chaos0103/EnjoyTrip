package hotplace.repository;

import attraction.AttractionInfo;
import hotplace.HotPlace;
import hotplace.UploadFile;
import member.Member;
import util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class HotPlaceJdbcRepository implements HotPlaceRepository {

    private static final HotPlaceRepository hotPlaceRepository = new HotPlaceJdbcRepository();
    private final DBConnectionUtil dbConnectionUtil;

    private HotPlaceJdbcRepository() {
        dbConnectionUtil = DBConnectionUtil.getInstance();
    }

    public static HotPlaceRepository getHotPlaceRepository() {
        return hotPlaceRepository;
    }

    @Override
    public int save(Long memberId, int contentId, HotPlace hotPlace) {
        int count = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "insert into hot_place(member_id, content_id, content_type_id, name, desc, visited_date, upload_file_name, store_file_name) values (?, ?, ?, ?, ?, ?, ?, ?);";

            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, memberId);
            pstmt.setInt(2, contentId);
            pstmt.setInt(3, hotPlace.getContentTypeId());
            pstmt.setString(4, hotPlace.getName());
            pstmt.setString(5, hotPlace.getDesc());
            pstmt.setString(6, hotPlace.getVisitedDate());
            pstmt.setString(7, hotPlace.getUploadFile().getUploadFileName());
            pstmt.setString(8, hotPlace.getUploadFile().getStoreFileName());

            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(pstmt, conn);
        }
        return count;
    }

    @Override
    public Optional<HotPlace> findById(Long hotPlaceId) {
        HotPlace hotPlace = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "select * from hot_place where hot_place_id = ?;";

            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, hotPlaceId);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                hotPlace = createHotplace(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(rs, pstmt, conn);
        }
        return Optional.ofNullable(hotPlace);
    }

    @Override
    public int remove(Long hotPlaceId) {
        int count = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "delete from hot_place where hot_place_id = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, hotPlaceId);

            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(pstmt, conn);
        }
        return count;
    }

    private HotPlace createHotplace(ResultSet rs) throws SQLException {
        return HotPlace.builder()
                .id(rs.getLong("hot_place_id"))
                .name(rs.getString("name"))
                .desc(rs.getString("desc"))
                .hit(rs.getInt("hit"))
                .visitedDate(rs.getString("visitedDate"))
                .uploadFile(
                        UploadFile.builder()
                                .uploadFileName(rs.getString("upload_file_name"))
                                .storeFileName(rs.getString("store_file_name"))
                                .build()
                )
                .createdDate(rs.getTimestamp("createdDate").toLocalDateTime())
                .lastModifiedDate(rs.getTimestamp("lastModifiedDate").toLocalDateTime())
                .contentTypeId(rs.getInt("content_type_id"))
                .member(Member.builder()
                        .id(rs.getLong("member_id"))
                        .build())
                .attractionInfo(
                        AttractionInfo.builder()
                                .id(rs.getInt("attraction_info_id"))
                                .build()
                )
                .build();
    }
}
