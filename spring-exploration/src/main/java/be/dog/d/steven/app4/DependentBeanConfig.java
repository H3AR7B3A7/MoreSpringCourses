package be.dog.d.steven.app4;

import be.dog.d.steven.app2.SimpleBean;
import be.dog.d.steven.app2.SimpleBeanImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DependentBeanConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleBeanImpl.class);

    @Bean
    SimpleBean simpleBean() {
        LOGGER.info("--> Creating simpleBean...");
        return new SimpleBeanImpl();
    }
    
    @Bean
    DependentBean dependentBean(){
        return new DependentBeanImpl(simpleBean());
    }
}