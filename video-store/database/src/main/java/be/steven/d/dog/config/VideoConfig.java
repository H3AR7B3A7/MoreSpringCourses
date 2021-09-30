package be.steven.d.dog.config;

import be.steven.d.dog.repository.VideoRepository;
import be.steven.d.dog.repository.impl.JdbcVideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class VideoConfig {
    
    @Autowired
    DataSource dataSource;
    
    @Bean
    VideoRepository videoRepository() {
        JdbcVideoRepository repository = new JdbcVideoRepository();
        repository.setDataSource(dataSource);
        return repository;
    }
}