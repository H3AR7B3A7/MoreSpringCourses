package be.dog.d.steven.conferenceapp.repository;

import be.dog.d.steven.conferenceapp.model.User;

public interface UserRepository {
    
    User save(User user);
}