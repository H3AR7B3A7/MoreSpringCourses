package be.dog.d.steven.app1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

class SimpleConfigTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleConfig.class);
    
    private ApplicationContext ctx;
    
    @BeforeEach
    void setUp() {
        ctx = new AnnotationConfigApplicationContext(SimpleConfig.class);
    }
    
    @Test
    void test() {
        for (String beanName: ctx.getBeanDefinitionNames()) {
            LOGGER.info("BEAN: " + beanName);
        }
    }
    
    @Test
    void test2() throws IOException {
        Resource resource = ctx.getResource("test.txt");
        File file = resource.getFile();

        Files.lines(file.toPath()).forEach(LOGGER::info);
    }
}