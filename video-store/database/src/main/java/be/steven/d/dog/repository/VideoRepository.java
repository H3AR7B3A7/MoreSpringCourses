package be.steven.d.dog.repository;

import be.steven.d.dog.Video;
import org.springframework.data.repository.Repository;

public interface VideoRepository extends Repository<Video, Integer> {
    
    Video findById(Integer id);
}