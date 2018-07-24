package acloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
public class SimpleBootstrapTest {
    public static void main(String[] args) {
        SpringApplication.run(SimpleBootstrap.class, args);
    }
}
