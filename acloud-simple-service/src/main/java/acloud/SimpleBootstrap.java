package acloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;




/**
 * 应用启动类
 */
//@ComponentScan(basePackages= {"acloud"}) 包修改微acloud，可以扫到所有类,common类
//@SpringBootApplication
//@EnableDiscoveryClient	//触发服务,发现
@SpringCloudApplication
public class SimpleBootstrap {
    public static void main(String[] args) {

        SpringApplication.run(SimpleBootstrap.class, args);
        
        
    }
}
