package be.steven.d.hondt.videostore;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
@Slf4j
public class VideoStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(VideoStoreApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(JdbcTemplate jdbcTemplate) {
        String sql1 = "SELECT count(*) FROM VIDEOS";
        String sql2 = "SELECT NAME FROM VIDEOS WHERE ID = 1";
        
        Integer numberOfVideos = jdbcTemplate.queryForObject(sql1, Integer.class);
        String firstVideo = jdbcTemplate.queryForObject(sql2, String.class);
        
        return args -> {
            log.info("There are {} videos.", numberOfVideos);
            log.info("The name of the first video is {}.", firstVideo);
        };
    }
}