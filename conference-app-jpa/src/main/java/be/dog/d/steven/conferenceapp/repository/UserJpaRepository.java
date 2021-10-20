package be.dog.d.steven.conferenceapp.repository;

import be.dog.d.steven.conferenceapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> { }