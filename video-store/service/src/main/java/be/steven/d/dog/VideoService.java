package be.steven.d.dog;

import be.steven.d.dog.config.VideoConfig;
import be.steven.d.dog.repository.VideoRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Import(VideoConfig.class)
@AllArgsConstructor
public class VideoService {
    private VideoRepository videoRepository;

    public Video getVideoById(Integer id) {
        return videoRepository.findById(id);
    }
    
    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }
}