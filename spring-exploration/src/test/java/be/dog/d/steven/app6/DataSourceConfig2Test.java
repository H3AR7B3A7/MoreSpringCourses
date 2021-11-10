package be.dog.d.steven.app6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;

class DataSourceConfig2Test {
    private ConfigurableApplicationContext ctx;

    @BeforeEach
    void setUp() {
        ctx = new AnnotationConfigApplicationContext(DataSourceConfig2.class);
        ctx.registerShutdownHook();
    }

    @Test
    void test() throws SQLException {
        DataSourceConfig2 dataSourceConfig2 = ctx.getBean(DataSourceConfig2.class);
        DataSource dataSource = dataSourceConfig2.dataSource();
        dataSource.getConnection().close();
    }
}