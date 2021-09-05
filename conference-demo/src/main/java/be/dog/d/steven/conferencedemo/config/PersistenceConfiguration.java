package be.dog.d.steven.conferencedemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceConfiguration {

    @Value("${DB_URL}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;
    
//    @Bean
//    public DataSource dataSource() {
//        DataSourceBuilder builder = DataSourceBuilder.create();
//        builder.url(url);
//        builder.username(username);
//        builder.password(password);
//        System.out.println("Custom datasource bean has been initialized and set.");
//        return builder.build();
//    }
}