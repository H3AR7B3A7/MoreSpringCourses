package be.dog.d.steven.conferenceapp.repository;

import be.dog.d.steven.conferenceapp.model.Course;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CourseRepositoryImpl implements CourseRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public Course save(Course course) {
        entityManager.persist(course);
        return course;
    }
}