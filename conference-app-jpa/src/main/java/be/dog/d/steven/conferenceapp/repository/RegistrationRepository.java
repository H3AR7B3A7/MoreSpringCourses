package be.dog.d.steven.conferenceapp.repository;

import be.dog.d.steven.conferenceapp.model.Registration;

import java.util.List;

public interface RegistrationRepository {

    Registration save(Registration registration);

    List<Registration> findAll();
}