//package acloud.simple.service.domain;
//
//import java.util.Map;
//
//import org.rocketmq.starter.RocketMQConsumerListener;
//import org.rocketmq.starter.annotation.RocketMQListener;
//import org.rocketmq.starter.core.consumer.MessageContext;
//import org.rocketmq.starter.core.consumer.RocketMQConsumerConfig;
//import org.rocketmq.starter.exception.ConsumeException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.cloud.stream.annotation.EnableBinding;
//import org.springframework.cloud.stream.annotation.StreamListener;
//import org.springframework.integration.annotation.IntegrationComponentScan;
//import org.springframework.integration.annotation.MessageEndpoint;
//import org.springframework.messaging.Message;
//import org.springframework.stereotype.Service;
//
//import acloud.simple.service.channel.Barista;
//
///**
// * rocketmq 发送接收方式
// * @author masen
// *
// */
//@Service
//public class RocketMqReceiver {
//	private Logger logger = LoggerFactory.getLogger(this.getClass());
//
//
//    //临时修改，待验证
//    @RocketMQListener(topic = "order-topic", consumerGroup = "my-consumer_test-topic-1")
//    public class MyConsumer1 implements RocketMQConsumerListener<Map> {
//        public void onMessage(Map message,MessageContext messageContext) {
//        	logger.info("received message: {}", message);
//        }
//
//
//
//        @Override public RocketMQConsumerConfig getConsumerConfig() {
//            return null;
//        }
//    }
//
//
//
//}
