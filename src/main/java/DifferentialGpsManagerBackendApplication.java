import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author tangqi
 */
@SpringBootApplication
@ServletComponentScan
@ComponentScan(basePackages="com.firedance.gps")
@MapperScan(basePackages = "com.firedance.gps.dao")
@EnableAutoConfiguration
public class DifferentialGpsManagerBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(DifferentialGpsManagerBackendApplication.class, args);
    }
}
