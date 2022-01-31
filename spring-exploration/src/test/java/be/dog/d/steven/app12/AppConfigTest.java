package be.dog.d.steven.app12;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppConfigTest {
    @Test
    void testBeanLifeCycleWithCGLIB() {
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        ctx.registerShutdownHook();
    }
}