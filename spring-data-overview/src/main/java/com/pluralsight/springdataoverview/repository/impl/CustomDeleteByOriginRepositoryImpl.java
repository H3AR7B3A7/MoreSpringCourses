package com.pluralsight.springdataoverview.repository.impl;

import com.pluralsight.springdataoverview.repository.CustomDeleteByOriginRepository;

import javax.persistence.EntityManager;

public class CustomDeleteByOriginRepositoryImpl implements CustomDeleteByOriginRepository {
    
    private final EntityManager entityManager;

    public CustomDeleteByOriginRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void deleteByOrigin(String origin) {
        entityManager.createNativeQuery("DELETE from flight WHERE origin = ?")
                .setParameter(1, origin)
                .executeUpdate();
    }
}