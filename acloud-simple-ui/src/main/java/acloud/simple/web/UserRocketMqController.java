package acloud.simple.web;

import java.util.Map;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.integration.support.MessageBuilder;
/**
 * 发送使用rocketmq
 * @author masen
 *
 */
@RestController
public class UserRocketMqController {
		
	@Resource
//	private RocketMQTemplate rocketMQTemplate;
	
	//用 Q 发送一个消息
	@RequestMapping(method = RequestMethod.POST, path = "/send2")
    public void write (@RequestBody Map<String, Object> msg){
		//@RequestBody  请求需要content 是json a
		System.out.println("msg ：" + msg);
		
//		rocketMQTemplate.convertAndSend("order-topic", msg);
//
//		rocketMQTemplate.send("order-topic", MessageBuilder.withPayload(msg).build());
		
		
		//demo 示例
//		rocketMQTemplate.convertAndSend("test-topic-1", "Hello, World!");
//      rocketMQTemplate.send("test-topic-1", MessageBuilder.withPayload("Hello, World! I'm from spring message").build());
//      rocketMQTemplate.convertAndSend("test-topic-2", new OrderPaidEvent("T_001", new BigDecimal("88.00")));
		
		System.out.println("发送");
    }

}
