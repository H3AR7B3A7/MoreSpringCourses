package be.dog.d.steven.conferenceapp.repository;

import be.dog.d.steven.conferenceapp.model.Registration;
import be.dog.d.steven.conferenceapp.model.RegistrationReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistrationJpaRepository extends JpaRepository<Registration, Long> {
    List<RegistrationReport> registrationReport();
}