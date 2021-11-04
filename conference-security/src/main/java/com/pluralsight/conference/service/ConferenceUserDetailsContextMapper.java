package com.pluralsight.conference.service;

import com.pluralsight.conference.model.ConferenceUserDetails;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.Collections;

@Service
public class ConferenceUserDetailsContextMapper implements UserDetailsContextMapper {
    
    private final DataSource dataSource;

    public ConferenceUserDetailsContextMapper(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private final static String LOAD_USER_BY_USERNAME_QUERY =
            "SELECT username, password, enabled, nickname FROM users WHERE username = ?";
    
    @Override
    public UserDetails mapUserFromContext(DirContextOperations dirContextOperations, String s, Collection<? extends GrantedAuthority> collection) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        
        final ConferenceUserDetails userDetails = new ConferenceUserDetails(
                dirContextOperations.getStringAttribute("uid"),
                "fake",
                Collections.emptyList()
        );
        
        jdbcTemplate.queryForObject(LOAD_USER_BY_USERNAME_QUERY, (resultSet, i) -> {
            userDetails.setNickname(resultSet.getString("nickname"));
            return userDetails;
        }, dirContextOperations.getStringAttribute("uid"));
        
        return userDetails;
    }

    @Override
    public void mapUserToContext(UserDetails userDetails, DirContextAdapter dirContextAdapter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}