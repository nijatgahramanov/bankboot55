package az.orient.bankboot55;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class Bankboot55Application {

    public static void main(String[] args) {
        SpringApplication.run(Bankboot55Application.class, args);
    }

}
