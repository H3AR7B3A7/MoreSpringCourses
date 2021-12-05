package be.dog.d.steven.app11;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class AppConfigTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(be.dog.d.steven.app9.EmployeeTest.class);

    @Test
    void testBeanLifeCycleWithCGLIB() {
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        ctx.registerShutdownHook();
    }
}