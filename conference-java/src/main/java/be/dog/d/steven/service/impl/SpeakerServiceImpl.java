package be.dog.d.steven.service.impl;

import be.dog.d.steven.model.Speaker;
import be.dog.d.steven.repository.SpeakerRepository;
import be.dog.d.steven.service.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("speakerService")
public class SpeakerServiceImpl implements SpeakerService {
    
    private SpeakerRepository repo;

    public SpeakerServiceImpl() {
        System.out.println("SpeakerServiceImpl no args constructor");
    }

    public SpeakerServiceImpl(SpeakerRepository repo) {
        System.out.println("SpeakerServiceImpl repository constructor");
        this.repo = repo;
    }

    @Override
    public List<Speaker> findAll(){
        return repo.findAll();
    }

    @Autowired
    public void setRepo(SpeakerRepository repo) {
        System.out.println("SpeakerServiceImpl setter");
        this.repo = repo;
    }
}