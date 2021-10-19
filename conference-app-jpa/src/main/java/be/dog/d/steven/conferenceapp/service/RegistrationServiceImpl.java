package be.dog.d.steven.conferenceapp.service;

import be.dog.d.steven.conferenceapp.model.Registration;
import be.dog.d.steven.conferenceapp.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    
    @Autowired
    private RegistrationRepository registrationRepository;
    
    @Override
    @Transactional
    public Registration addRegistration(Registration registration) {
        return registrationRepository.save(registration);
    }
}