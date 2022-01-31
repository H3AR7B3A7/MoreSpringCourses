package be.dog.d.steven.app12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MyBean implements InitializingBean {
    private final Logger LOGGER = LoggerFactory.getLogger(MyBean.class);
    
    // The bean is created, then dependencies are injected and then the init method is called.
    
    @Override
    public void afterPropertiesSet() throws Exception {
        // This way tightly couples the application to the Spring container and is bad practice.
        LOGGER.info("2: MyBean.afterPropertiesSet()");
    }
    
    @PostConstruct
    public void myInitMethod() {
        LOGGER.info("1: MyBean.myInitMethod()");
    }

    public void myOtherInitMethod() {
        LOGGER.info("3: MyBean.myOtherInitMethod()");
    }
}