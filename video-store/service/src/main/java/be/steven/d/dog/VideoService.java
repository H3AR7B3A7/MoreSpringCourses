package be.steven.d.dog;

import be.steven.d.dog.config.VideoConfig;
import be.steven.d.dog.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;

@Service
@Import(VideoConfig.class)
public class VideoService {
    private VideoRepository videoRepository;

    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public Video getVideoById(Integer id) {
        return videoRepository.findById(id);
    }
}