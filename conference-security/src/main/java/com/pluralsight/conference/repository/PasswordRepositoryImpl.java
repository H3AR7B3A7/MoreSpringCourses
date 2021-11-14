package com.pluralsight.conference.repository;

import com.pluralsight.conference.model.Password;
import com.pluralsight.conference.model.ResetToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class PasswordRepositoryImpl implements PasswordRepository {
    
    @Autowired
    private DataSource dataSource;
    
    @Override
    public void saveToken(ResetToken resetToken) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO reset_tokens (email, username, token, expiry_date) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                resetToken.getEmail(),
                resetToken.getUsername(),
                resetToken.getToken(),
                resetToken.calculateExpiryDate(resetToken.EXPIRATION));
    }

    @Override
    public ResetToken findByToken(String token) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        String sql = "SELECT email, username, token, expiry_date FROM reset_tokens WHERE token = ?";
        return template.queryForObject(sql,
                (resultSet, i) -> {
                    ResetToken rsToken = new ResetToken();
                    rsToken.setEmail(resultSet.getString("email"));
                    rsToken.setUsername(resultSet.getString("username"));
                    rsToken.setToken(resultSet.getString("token"));
                    rsToken.setExpiryDate(resultSet.getTimestamp("expiry_date"));
                    return rsToken;
                },
                token);
    }

    @Override
    public void update(Password password, String username) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        String sql = "UPDATE users SET password = ? WHERE username = ?";
        template.update(sql, password.getPassword(), username);
    }
}