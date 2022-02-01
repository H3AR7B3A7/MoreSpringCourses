package be.dog.d.steven.app13;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component
public class MyBean implements DisposableBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyBean.class);
    
    // Only for singleton beans
    
    @Override
    public void destroy() throws Exception {
        LOGGER.info("2: MyBean.destroy()");
    }
    
    @PreDestroy
    public void preDestroy() {
        LOGGER.info("1: MyBean.preDestroy()");
    }
    
    private void myOtherDestroy() {
        LOGGER.info("3: MyBean.myOtherDestroy()");
    }
}