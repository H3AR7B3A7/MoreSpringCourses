package com.pluralsight.conferencedemo.repositories;

import com.pluralsight.conferencedemo.models.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SessionRepository {
    @Autowired
    private SessionsJpaRepository jpaRepository;
    
    @PersistenceContext
    private EntityManager entityManager;

    public Session create(Session session) {
//        entityManager.persist(session);
//        entityManager.flush();
//        return session;
        return jpaRepository.saveAndFlush(session);
    }

    public Session update(Session session) {
//        session = entityManager.merge(session);
//        entityManager.flush();
//        return session;
        return jpaRepository.saveAndFlush(session);
    }

    public void delete(Long id) {
//        entityManager.remove(find(id));
//        entityManager.flush();
        jpaRepository.deleteById(id);
    }

    public Session find(Long id) {
//        return entityManager.find(Session.class, id);
        return jpaRepository.getOne(id);
    }

    public List<Session> list() {
//        return entityManager.createQuery("select s from Session s").getResultList();
        return jpaRepository.findAll();
    }

    public List<Session> getSessionsThatHaveName(String name) {
//        List<Session> ses = entityManager
//                .createQuery("select s from Session s where s.sessionName like :name")
//                .setParameter("name", "%" + name + "%").getResultList();
        return jpaRepository.findBySessionNameContains(name);
    }

    public List<Session> findBySessionLengthNot(Integer length) {
        return jpaRepository.findBySessionLengthNot(length);
    }
    
    public List<Session> findBySessionNameNotLike(String name){
        return jpaRepository.findBySessionNameNotLike(name);
    }
    
    public List<Session> findBySessionNameStartingWith(String name){
        return jpaRepository.findBySessionNameStartingWith(name);
    }

    public List<Session> findBySessionLengthLessThan(Integer length){
        return jpaRepository.findBySessionLengthLessThan(length);   
    }


    public Page<Session> getSessionsWithName(String name, PageRequest pageRequest) {
        return jpaRepository.getSessionsWithName(name, pageRequest);
    }
    
    public List<Session> customGetSessions() {
        return jpaRepository.customGetSessions();
    }
}