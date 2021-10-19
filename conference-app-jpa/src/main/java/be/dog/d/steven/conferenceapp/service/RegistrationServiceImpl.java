package be.dog.d.steven.conferenceapp.service;

import be.dog.d.steven.conferenceapp.model.Course;
import be.dog.d.steven.conferenceapp.model.Registration;
import be.dog.d.steven.conferenceapp.repository.CourseRepository;
import be.dog.d.steven.conferenceapp.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    
    @Autowired
    private RegistrationRepository registrationRepository;
    
    @Autowired
    private CourseRepository courseRepository;
    
    @Override
    @Transactional
    public Registration addRegistration(Registration registration) {
        registration = registrationRepository.save(registration);
    
        Course course = new Course();
        course.setName("Spring");
        course.setDescription("An intro course...");
        course.setRegistration(registration);
    
        courseRepository.save(course);
        
        return registration;
    }
}