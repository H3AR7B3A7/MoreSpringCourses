package be.dog.d.steven.service.impl;

import be.dog.d.steven.model.Speaker;
import be.dog.d.steven.repository.SpeakerRepository;
import be.dog.d.steven.repository.impl.HibernateSpeakerRepositoryImpl;
import be.dog.d.steven.service.SpeakerService;

import java.util.List;

public class SpeakerServiceImpl implements SpeakerService {
    
    private SpeakerRepository repo;

    public SpeakerServiceImpl() {
    }

    public SpeakerServiceImpl(SpeakerRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Speaker> findAll(){
        return repo.findAll();
    }

    public void setSpeakerRepository(SpeakerRepository repo) {
        this.repo = repo;
    }
}