package com.pluralsight.conferencedemo.repositories;

import com.pluralsight.conferencedemo.models.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpeakerJpaRepository extends JpaRepository<Speaker, Long> {
    
    List<Speaker> findByFirstNameAndLastName(String first, String last);
    
    List<Speaker> findByFirstNameOrLastName(String first, String last);
    
    List<Speaker> findBySpeakerPhotoNull();
    
    List<Speaker> findByCompanyIn(List<String> companies);
}