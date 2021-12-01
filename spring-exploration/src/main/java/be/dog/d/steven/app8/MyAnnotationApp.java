package be.dog.d.steven.app8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyAnnotationApp {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MyXmlApp.class);

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(MyAnnotationConfiguredBean.class);
        for (String beanName: ctx.getBeanDefinitionNames()) {
            LOGGER.info("BEAN: " + beanName);
        }
    }
}