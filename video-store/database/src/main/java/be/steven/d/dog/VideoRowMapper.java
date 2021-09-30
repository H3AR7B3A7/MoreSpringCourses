package be.steven.d.dog;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VideoRowMapper implements RowMapper<Video> {

    @Override
    public Video mapRow(ResultSet rs, int rowNum) throws SQLException {
        Video video = new Video();
        video.setId(rs.getInt("ID"));
        video.setName(rs.getString("NAME"));
        video.setAmount(rs.getInt("AMOUNT"));
        return video;
    }
}