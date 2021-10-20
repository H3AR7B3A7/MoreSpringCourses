package be.dog.d.steven.conferenceapp.service;

import be.dog.d.steven.conferenceapp.model.Course;
import be.dog.d.steven.conferenceapp.model.Registration;
import be.dog.d.steven.conferenceapp.model.RegistrationReport;
import be.dog.d.steven.conferenceapp.repository.CourseRepository;
import be.dog.d.steven.conferenceapp.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    
    @Autowired
    private RegistrationRepository registrationRepository;
    
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Registration> findAll() {
        return registrationRepository.findAll();
    }

    @Override
    public List<RegistrationReport> findAllReports() {
        return registrationRepository.findAllReports();
    }

    @Override
    @Transactional
    public Registration addRegistration(Registration registration) {
        registration = registrationRepository.save(registration);
    
        if (registration.getId() == null) {
            Course course = new Course();
            course.setName("Spring");
            course.setDescription("An intro course...");
            course.setRegistration(registration);

            courseRepository.save(course);
        }
        return registration;
    }
}