package mptest;

import com.ld.config.ApplicationConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;

public class T {
    @Test
    public void testConnection() throws SQLException {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        DataSource dataSource = (DataSource) ctx.getBean("dataSource");
        System.out.println(dataSource.getConnection());
    }

}
