package acloud.simple.service.domain;

import java.util.Map;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessageEndpoint;

/**
 * source,sink 方式发送接收消息
 * @author masen
 *
 */
@EnableBinding(Sink.class)
@IntegrationComponentScan
@MessageEndpoint
public class SinkReceiverService {

	//代表一个消息输入
	@StreamListener(Sink.INPUT)
    public void accept(Map<String, Object> msg){
    	System.out.println("msg ：" + msg);
        System.out.println(msg.get("msg").toString() + ":" + msg.get("name"));
    }
    
}
