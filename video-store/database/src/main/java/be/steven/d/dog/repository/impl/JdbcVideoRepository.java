package be.steven.d.dog.repository.impl;

import be.steven.d.dog.Video;
import be.steven.d.dog.VideoRowMapper;
import be.steven.d.dog.repository.VideoRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcVideoRepository implements VideoRepository {
    
    private JdbcTemplate jdbcTemplate;
    
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Video> findAll() {
        String sql = "SELECT * FROM VIDEOS";
        return jdbcTemplate.query(sql, new VideoRowMapper());
    }

    @Override
    public Video findById(Integer id) {
        String sql = "SELECT * FROM VIDEOS WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, new VideoRowMapper(), id);
    }
}