package com.pluralsight.conference.repository;

import com.pluralsight.conference.model.Account;
import com.pluralsight.conference.model.ConferenceUserDetails;
import com.pluralsight.conference.model.VerificationToken;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

    private final DataSource dataSource;

    public AccountRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Account create(Account account) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        String sql = 
                "INSERT INTO accounts (username, password, email, firstname, lastname) " +
                "VALUES (?,?,?,?,?)";
        template.update(sql,
                account.getUsername(),
                account.getPassword(),
                account.getEmail(),
                account.getFirstName(),
                account.getLastName());

        return account;
    }

    @Override
    public void saveToken(VerificationToken verificationToken) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        String sql =
                "INSERT INTO verification_tokens (username , token, expiry_date) " +
                "VALUES (?,?,?)";
        template.update(sql,
                verificationToken.getUsername(),
                verificationToken.getToken(),
                verificationToken.calculateExpiryDate(VerificationToken.EXPIRATION));

    }

    @Override
    public VerificationToken findByToken(String token) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        String sql = "SELECT username, token, expiry_date FROM verification_tokens WHERE token = ?";
        return template.queryForObject(sql,
                (resultSet, i) -> {
                    VerificationToken rsToken = new VerificationToken();
                    rsToken.setUsername(resultSet.getString("username"));
                    rsToken.setToken(resultSet.getString("token"));
                    rsToken.setExpiryDate(resultSet.getTimestamp("expiry_date"));
                    return rsToken;
                },
                token);
    }

    @Override
    public Account findByUsername(String username) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        String sql =
                "SELECT username, firstname, lastname, password FROM accounts WHERE username = ?";
        return template.queryForObject(sql,
                (resultSet, i) -> {
                    Account account1 = new Account();
                    account1.setUsername(resultSet.getString("username"));
                    account1.setFirstName(resultSet.getString("firstname"));
                    account1.setLastName(resultSet.getString("lastname"));
                    account1.setPassword(resultSet.getString("password"));
                    return account1;
                },
                username);
    }

    @Override
    public void createUserDetails(ConferenceUserDetails userDetails) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        String sql=
                "INSERT INTO users (username , password, enabled) " +
                "VALUES (?,?,?)";
        template.update(sql, 
                userDetails.getUsername(),
                userDetails.getPassword(),
                1);
    }

    @Override
    public void createAuthorities(ConferenceUserDetails userDetails) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO authorities(username, authority) VALUES (?, ?)";
        for (GrantedAuthority grantedAuthority : userDetails.getAuthorities()) {
            template.update(sql,
                    userDetails.getUsername(),
                    grantedAuthority.getAuthority());
        }
    }

    @Override
    public void delete(Account account) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        String sql = "DELETE FROM accounts WHERE username = ?";
        template.update(sql, account.getUsername());
    }

    @Override
    public void deleteToken(String token) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        String sql = "DELETE FROM verification_tokens WHERE token = ?";
        template.update(sql, token);
    }
}