package acloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.converter.CompositeMessageConverterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.context.IntegrationContextUtils;
import org.springframework.integration.support.converter.ConfigurableCompositeMessageConverter;

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

    @Bean(name = IntegrationContextUtils.ARGUMENT_RESOLVER_MESSAGE_CONVERTER_BEAN_NAME)
    public ConfigurableCompositeMessageConverter configurableCompositeMessageConverter(CompositeMessageConverterFactory factory){
        return new ConfigurableCompositeMessageConverter(factory.getMessageConverterForAllRegistered().getConverters());
    }
}
