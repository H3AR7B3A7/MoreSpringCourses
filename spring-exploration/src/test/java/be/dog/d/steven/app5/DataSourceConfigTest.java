package be.dog.d.steven.app5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;

class DataSourceConfigTest {
    private ConfigurableApplicationContext ctx;

    @BeforeEach
    void setUp() {
        ctx = new AnnotationConfigApplicationContext(DataSourceConfig.class);
        ctx.registerShutdownHook();
    }

    @Test
    void test() throws SQLException {
        DataSourceConfig dataSourceConfig = ctx.getBean(DataSourceConfig.class);
        DataSource dataSource = dataSourceConfig.dataSource();
        dataSource.getConnection().close();
    }
}