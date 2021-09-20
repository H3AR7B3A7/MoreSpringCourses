package be.dog.d.steven.conferenceapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class ConferenceConfiguration implements WebMvcConfigurer {
    
    @Bean
    // Created automatically using values in application.properties when not defined
    public ViewResolver viewResolver() { 
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setPrefix("/WEB-INF/jsp/");
        bean.setSuffix(".jsp");
        bean.setOrder(0);
        return bean;
    }

    @Override
    // http://localhost:8080/conference/files/Resume.pdf
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/files/**")
                .addResourceLocations("/WEB-INF/pdf/");
        registry
                .addResourceHandler("/styles/**")
                .addResourceLocations("/WEB-INF/styles/");
    }
}