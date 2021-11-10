package be.dog.d.steven.app4;

import be.dog.d.steven.app2.SimpleBean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class DependentBeanConfigTest {
    private ConfigurableApplicationContext ctx;

    @BeforeEach
    void setUp() {
        ctx = new AnnotationConfigApplicationContext(DependentBeanConfig.class);
        ctx.registerShutdownHook();
    }

    @Test
    void testOneBeanConfig() {
        DependentBean beanOne = ctx.getBean(DependentBean.class);
        DependentBean beanTwo = ctx.getBean(DependentBean.class);
        Assertions.assertEquals(beanOne, beanTwo);
    }

    @Test
    void testSimpleBeans() {
        DependentBeanConfig dependentBeanConfig = ctx.getBean(DependentBeanConfig.class);
        assertNotNull(dependentBeanConfig);
        
        SimpleBean simpleBean = dependentBeanConfig.simpleBean();
        assertNotNull(simpleBean);
    }
}