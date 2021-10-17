package com.pluralsight.conferencedemo.repositories;

import com.pluralsight.conferencedemo.models.Session;

import java.util.List;

public interface CustomSessionJpaRepository {
    List<Session> customGetSessions();
}