package com.pluralsight.conferencedemo.repositories;

import com.pluralsight.conferencedemo.models.Session;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomSessionJpaRepository {
    List<Session> customGetSessions();
    
    List<String[]> test();
}