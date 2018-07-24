package acloud;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/** 
* @author masen E-mail:masen.miao@gmail.com 
* @version 创建时间：2018年3月6日 下午5:50:42 
* 
*/
@SpringCloudApplication
@EnableFeignClients
public class SimpleUIBootstrapTest {

	public static void main(String[] args) {
		SpringApplication.run(SimpleUIBootstrap.class, args);
	}
	
}
