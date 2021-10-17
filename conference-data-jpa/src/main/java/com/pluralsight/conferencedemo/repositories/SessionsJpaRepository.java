package com.pluralsight.conferencedemo.repositories;

import com.pluralsight.conferencedemo.models.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SessionsJpaRepository extends JpaRepository<Session, Long>, CustomSessionJpaRepository {
    
    List<Session> findBySessionNameContains(String name);
    
    List<Session> findBySessionLengthNot(Integer length);

    List<Session> findBySessionNameNotLike(String name);

    List<Session> findBySessionNameStartingWith(String name);

    List<Session> findBySessionLengthLessThan(Integer length);
    
    @Query("SELECT s FROM Session s WHERE s.sessionName LIKE %:name")
    Page<Session> getSessionsWithName(@Param("name") String name, Pageable pageable);
}