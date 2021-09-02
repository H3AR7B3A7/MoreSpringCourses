package be.dog.d.steven.conferencedemo.repositories;

import be.dog.d.steven.conferencedemo.models.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeakerRepository extends JpaRepository<Speaker, Long> {
}