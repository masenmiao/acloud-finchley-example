package acloud.simple.service.domain;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.messaging.Message;

import acloud.simple.service.channel.Barista;

/**
 * 自定义消息channel 发送接收方式
 * @author masen
 *
 */
@EnableBinding(Barista.class)
@IntegrationComponentScan
@MessageEndpoint
public class StreamReceiver {
	private Logger logger = LoggerFactory.getLogger(this.getClass());  
	//代表一个消息输入
	@StreamListener(Barista.INPUT_CHANNEL)
	public void receiver(Message<Object> message){
		System.out.println("message:" + message);
		Object obj = message.getPayload();
		//todo message.getPayload()需要从byte 转 String 到 Object
		//可以使用CompositeMessageConverterFactory
		this.logger.info("obj : {}",obj);
        
    }

}
