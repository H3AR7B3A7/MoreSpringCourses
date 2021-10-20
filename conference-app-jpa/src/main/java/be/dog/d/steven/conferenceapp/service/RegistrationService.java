package be.dog.d.steven.conferenceapp.service;

import be.dog.d.steven.conferenceapp.model.Registration;
import be.dog.d.steven.conferenceapp.model.RegistrationReport;

import java.util.List;

public interface RegistrationService {
    
    Registration addRegistration(Registration registration);

    List<Registration> findAll();

    List<RegistrationReport> findAllReports();
}