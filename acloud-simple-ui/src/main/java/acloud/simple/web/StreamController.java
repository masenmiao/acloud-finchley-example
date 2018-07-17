/*
 * Copyright 2012-2020 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * @author lzhoumail@126.com/zhouli
 * Git http://git.oschina.net/zhou666/spring-cloud-7simple
 */

package acloud.simple.web;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import acloud.simple.service.channel.Barista;

/**
 * spring cloud stream 发送  , 使用rabbitmq
 * @author masen
 *
 */

@EnableBinding(Barista.class)//绑定一个输出
@RestController
public class StreamController {
		
	 @Autowired  
	private Barista source;
	
	//用 Q 发送一个消息
	@RequestMapping(method = RequestMethod.POST, path = "/send1")
    public void write (@RequestBody Map<String, Object> msg){
		//@RequestBody  请求需要content 是json
		System.out.println("msg ：" + msg);
		source.logOutPut().send(MessageBuilder.withPayload(msg).build());
		System.out.println("发送");  
    }

}
