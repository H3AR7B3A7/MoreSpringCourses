package be.dog.d.steven;

import be.dog.d.steven.repository.SpeakerRepository;
import be.dog.d.steven.repository.impl.HibernateSpeakerRepositoryImpl;
import be.dog.d.steven.service.SpeakerService;
import be.dog.d.steven.service.impl.SpeakerServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    
    @Bean(name = "speakerService")
    public SpeakerService getSpeakerService() {
        SpeakerServiceImpl service = new SpeakerServiceImpl(getSpeakerRepository());
//        service.setRepo(getSpeakerRepository());
        return service;
    }
    
    @Bean(name = "speakerRepository")
    public SpeakerRepository getSpeakerRepository() {
        return new HibernateSpeakerRepositoryImpl();
    }
}