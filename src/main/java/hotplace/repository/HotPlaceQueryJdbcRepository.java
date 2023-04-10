package hotplace.repository;

import hotplace.HotPlace;
import hotplace.dto.HotPlaceListDto;
import hotplace.dto.HotPlaceSearch;
import util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HotPlaceQueryJdbcRepository implements HotPlaceQueryRepository {

    private static final HotPlaceQueryRepository hotPlaceQueryRepository = new HotPlaceQueryJdbcRepository();
    private final DBConnectionUtil dbConnectionUtil;

    private HotPlaceQueryJdbcRepository() {
        dbConnectionUtil = DBConnectionUtil.getInstance();
    }

    public static HotPlaceQueryRepository getHotPlaceQueryRepository() {
        return hotPlaceQueryRepository;
    }

    @Override
    public List<HotPlaceListDto> findByCondition(HotPlaceSearch condition) {
        List<HotPlaceListDto> hotPlaces = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbConnectionUtil.getConnection();
            String sql = "select hp.hot_place_id, hp.name, hp.desc, hp.hit, hp.store_file_name, m.nickname, hp.created_date" +
                    " from hot_place hp" +
                    " join member m" +
                    " on hp.member_id = m.member_id" +
                    " where m.nickname like ?" +
                    " or hp.name like ?" +
                    " or hp.desc like ?";

            if (condition.getSortCondition() == 2) {
                sql += " order by hp.hit desc";
            } else {
                sql += " order by hp.created_date desc";
            }

            //작성자, 제목, 내용
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, '%' + condition.getName() + '%');
            pstmt.setString(2, '%' + condition.getName() + '%');
            pstmt.setString(3, '%' + condition.getName() + '%');

            rs = pstmt.executeQuery();
            while (rs.next()) {
                HotPlaceListDto hotPlaceListDto = HotPlaceListDto.builder()
                        .hotPlaceId(rs.getLong("hot_place_id"))
                        .name(rs.getString("name"))
                        .desc(rs.getString("desc"))
                        .hit(rs.getInt("hit"))
                        .storeFileName(rs.getString("store_file_name"))
                        .nickname(rs.getString("nickname"))
                        .createdDate(dateFormat(rs.getTimestamp("created_date").toLocalDateTime()))
                        .build();
                hotPlaces.add(hotPlaceListDto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectionUtil.close(rs, pstmt, conn);
        }
        return hotPlaces;
    }

    private String dateFormat(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
