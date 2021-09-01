package be.dog.d.steven.repository.impl;

import be.dog.d.steven.model.Speaker;
import be.dog.d.steven.repository.SpeakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Repository("speakerRepository")
public class HibernateSpeakerRepositoryImpl implements SpeakerRepository {
    
    @Autowired
    private Calendar cal;
    
    @Value("#{ T(java.lang.Math).random() * 100}")
    private double seedNum;
    
    @Override
    public List<Speaker> findAll() {
        List<Speaker> speakers = new ArrayList<>();
        Speaker speaker = new Speaker();
        
        speaker.setFirstName("Steven");
        speaker.setLastName("D'Hondt");
        speaker.setSeedNum(seedNum);

        System.out.println("Cal: " + cal.getTime());
        
        speakers.add(speaker);
        
        return speakers;
    }
}