package com.pluralsight.conferencedemo.models;

import com.pluralsight.conferencedemo.repositories.SessionRepository;
import com.pluralsight.conferencedemo.repositories.SessionsJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.data.domain.Sort.Direction.DESC;

@SpringBootTest
public class SessionTest {
    @Autowired
    private SessionRepository repository;

    @Test
    public void test() throws Exception {
        List<Session> sessions = repository.getSessionsThatHaveName("Java");
        assertTrue(sessions.size() > 0);
    }
    
    @Test
    public void jpaNotTest() throws Exception {
        List<Session> sessions = repository.findBySessionLengthNot(30);
        assertTrue(sessions.size() > 0);
    }
    
    @Test
    public void jpaNotLikeTest() throws Exception {
        List<Session> sessions = repository.findBySessionNameNotLike("Java%");
        assertTrue(sessions.size() > 0);
    }
    
    @Test
    public void jpaStartingWithTest() throws Exception {
        List<Session> sessions = repository.findBySessionNameStartingWith("A");
        assertTrue(sessions.size() > 0);
    }
    
    @Test
    public void jpaLessThanTest() throws Exception {
        List<Session> sessions = repository.findBySessionLengthLessThan(45);
        assertTrue(sessions.size() > 0);
    }

    @Test
    void jpaPageableTest() {
        Page<Session> page = repository.getSessionsWithName("S", PageRequest.of(1, 5, Sort.by(DESC, "sessionLength")));
        assertTrue(page.getTotalElements() > 0);
        assertTrue(page.getTotalPages() > 0);
    }

    @Test
    void customRepositoryTest() {
        List<Session> sessions = repository.customGetSessions();
        assertTrue(sessions.size() > 0);
    }
}