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
    public int save(HotPlace hotPlace) {
        int count = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "insert into hot_place values (?, ?, ?, ?, ?);";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, hotPlace.getName());
            pstmt.setString(2, hotPlace.getDesc());
            pstmt.setString(3, hotPlace.getVisitedDate());
            pstmt.setString(4, hotPlace.getUploadFile().getUploadFileName());
            pstmt.setString(5, hotPlace.getUploadFile().getStoreFileName());

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
