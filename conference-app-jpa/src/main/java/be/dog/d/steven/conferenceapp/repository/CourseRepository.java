package be.dog.d.steven.conferenceapp.repository;

import be.dog.d.steven.conferenceapp.model.Course;

public interface CourseRepository {

    Course save(Course course);
}