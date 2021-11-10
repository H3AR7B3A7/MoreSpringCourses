package be.dog.d.steven.app3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class OneBeanConfig2Test {
    private ApplicationContext ctx;

    @BeforeEach
    void setUp() {
        ctx = new AnnotationConfigApplicationContext(OneBeanConfig2.class);
    }

    @Test
    void testOneBeanConfig() {
        MyComponent beanOne = ctx.getBean(MyComponent.class);
        MyComponent beanTwo = ctx.getBean(MyComponent.class);
    }
}