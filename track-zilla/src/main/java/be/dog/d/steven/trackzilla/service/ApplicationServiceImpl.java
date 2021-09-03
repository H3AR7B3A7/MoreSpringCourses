package be.dog.d.steven.trackzilla.service;

import be.dog.d.steven.trackzilla.entity.Application;
import be.dog.d.steven.trackzilla.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    public Iterable<Application> listApplications() {
        return applicationRepository.findAll();
    }

}
