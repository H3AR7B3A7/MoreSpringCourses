package be.dog.d.steven.app2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class OneBeanConfigTest {
    
    private ApplicationContext ctx;
    
    @BeforeEach
    void setUp() {
        ctx = new AnnotationConfigApplicationContext(OneBeanConfig.class);
    }
    
    @Test
    void testOneBeanConfig() {
        SimpleBean beanOne = ctx.getBean(SimpleBean.class);
        SimpleBean beanTwo = ctx.getBean(SimpleBean.class);
        Assertions.assertEquals(beanOne, beanTwo);
    }

}