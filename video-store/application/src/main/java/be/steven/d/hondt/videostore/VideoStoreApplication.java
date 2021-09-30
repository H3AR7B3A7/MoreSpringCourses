package be.steven.d.hondt.videostore;

import be.steven.d.dog.Video;
import be.steven.d.dog.VideoController;
import be.steven.d.dog.config.VideoConfig;
import be.steven.d.dog.repository.VideoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication(scanBasePackageClasses = {VideoConfig.class, VideoController.class})
@Slf4j
@AllArgsConstructor
//@Import({VideoConfig.class, VideoController.class})
public class VideoStoreApplication {
    
    private VideoRepository videoRepository;

    public static void main(String[] args) {
        SpringApplication.run(VideoStoreApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(JdbcTemplate jdbcTemplate) {
        String sql1 = "SELECT count(*) FROM VIDEOS";
        String sql2 = "SELECT NAME FROM VIDEOS WHERE ID = 0";
        
        Integer numberOfVideos = jdbcTemplate.queryForObject(sql1, Integer.class);
        String firstVideo = jdbcTemplate.queryForObject(sql2, String.class);
        Video video = videoRepository.findById(0);
        
        return args -> {
            log.warn("There are {} videos.", numberOfVideos);
            log.warn("The name of the first video is {}.", firstVideo);
            log.warn("{}", video.getName());
        };
    }
}