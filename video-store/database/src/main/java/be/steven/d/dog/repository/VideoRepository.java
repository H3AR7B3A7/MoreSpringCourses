package be.steven.d.dog.repository;

import be.steven.d.dog.Video;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface VideoRepository extends Repository<Video, Integer> {
    
    Video findById(Integer id);
    
    List<Video> findAll();
}