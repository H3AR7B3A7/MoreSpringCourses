package be.dog.d.steven.app7;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class EmployeeTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeTest.class);

    @Test
    void testBeanLifeCycleWithCGLIB() {
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        ctx.registerShutdownHook();
        
        Employee employee = ctx.getBean(Employee.class);
        assertNotNull(employee);
        
        Salary salary = employee.getSalary();
        assertNotNull(salary);
        
        LOGGER.info("Salary bean actual type: {}", salary.getClass());
        LOGGER.info("Salary: {}", salary.getAmount());
        LOGGER.info("Salary: {}", salary.getAmount());
        LOGGER.info("Salary: {}", salary.getAmount());
    }

    @Test
    void testBeanLifeCycleWithJDKDynamicProxy() {
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        ctx.registerShutdownHook();

        Employee2 employee = ctx.getBean(Employee2.class);
        assertNotNull(employee);

        Sal salary = employee.getSalary();
        assertNotNull(salary);

        LOGGER.info("Salary bean actual type: {}", salary.getClass());
        LOGGER.info("Salary: {}", salary.getAmount());
        LOGGER.info("Salary: {}", salary.getAmount());
        LOGGER.info("Salary: {}", salary.getAmount());
    }
}