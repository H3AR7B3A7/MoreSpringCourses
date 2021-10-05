package be.steven.d.dog.ridesharing.repository.impl;

import be.steven.d.dog.ridesharing.model.Ride;
import be.steven.d.dog.ridesharing.repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("rideRepository")
public class RideRepositoryImpl implements RideRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public List<Ride> getRides() {
        String sql = "SELECT * FROM ride";
        return jdbcTemplate.query(sql, new RowMapper<Ride>() {
            @Override
            public Ride mapRow(ResultSet rs, int rowNum) throws SQLException {
                Ride ride = new Ride();
                ride.setId(rs.getInt("id"));
                ride.setName(rs.getString("name"));
                ride.setDuration(rs.getInt("duration"));
                return ride;
            }
        });
    }

    @Override
    public Ride createRide(Ride ride) {
//        String sql = "INSERT INTO ride (name, duration) values (?,?)";
//        jdbcTemplate.update(sql, ride.getName(), ride.getDuration());
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
        
        List<String> columns = new ArrayList<>();
        columns.add("name");
        columns.add("duration");
        
        insert.setTableName("ride");
        insert.setColumnNames(columns);

        Map<String, Object> data = new HashMap<>();
        data.put("name", ride.getName());
        data.put("duration", ride.getDuration());
        
        insert.setGeneratedKeyName("id");
        Number key = insert.executeAndReturnKey(data);
        ride.setId(key.intValue());
        
        return ride;
    }
}