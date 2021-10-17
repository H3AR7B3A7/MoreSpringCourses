package com.pluralsight.conferencedemo.repositories;

import com.pluralsight.conferencedemo.models.Session;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CustomSessionJpaRepositoryImpl implements CustomSessionJpaRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Session> customGetSessions() {
        return entityManager.createQuery("SELECT s FROM Session s").getResultList();
    }

    @Override
    public List<String[]> test() {
        return entityManager.createQuery("SELECT DISTINCT s.sessionName, s.sessionDescription FROM Session s WHERE s.sessionName IS NOT NULL ORDER BY s.sessionName").getResultList();
    }
}