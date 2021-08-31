package be.dog.d.steven.repository;

import be.dog.d.steven.model.Speaker;

import java.util.List;

public interface SpeakerRepository {
    List<Speaker> findAll();
}