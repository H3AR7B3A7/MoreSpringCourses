package be.dog.d.steven.conferencedemo.repositories;

import be.dog.d.steven.conferencedemo.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
}