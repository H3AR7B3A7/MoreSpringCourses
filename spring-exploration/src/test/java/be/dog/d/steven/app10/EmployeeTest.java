package be.dog.d.steven.app10;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EmployeeTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(be.dog.d.steven.app9.EmployeeTest.class);

    @Test
    void testBeanLifeCycleWithCGLIB() {
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        ctx.registerShutdownHook();

        be.dog.d.steven.app10.Employee employee = ctx.getBean(Employee.class);
        assertNotNull(employee);

        Sal salary = employee.getSalary();
        assertNotNull(salary);

        LOGGER.info("Salary bean actual type: {}", salary.getClass());
        LOGGER.info("Salary: {}", salary.getAmount());
        LOGGER.info("Salary: {}", salary.getAmount());
        LOGGER.info("Salary: {}", salary.getAmount());
    }
}