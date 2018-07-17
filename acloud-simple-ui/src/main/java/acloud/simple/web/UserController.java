/*
 * Copyright 2012-2020 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * @author lzhoumail@126.com/zhouli
 * Git http://git.oschina.net/zhou666/spring-cloud-7simple
 */

package acloud.simple.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancerExchangeFilterFunction;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerResponse;

import acloud.simple.service.AsyncUserCallService;
import acloud.simple.service.UserCallService;
import acloud.simple.service.data.User;
import acloud.simple.service.spe.UserFeignService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@EnableBinding(Source.class) // 绑定一个输出
@RestController
public class UserController {

	final String SERVICE_NAME = "cloud-simple-service";

	@Autowired
	UserCallService userCallService;

	@Autowired
	UserFeignService feignService;

	// @Autowired
	// FeignUserService feignUserService;

	@Autowired
	AsyncUserCallService asynUserService;

	@Autowired
	private Source source;

	@Autowired
    private LoadBalancerExchangeFilterFunction lbFunction;
	
	// WebClient调用，需要知道对方服务ID
	@RequestMapping(value = "/users")
	public Flux<User> queryUserAll() {
//		List<User> users = userCallService.readUserInfo();
		return WebClient.builder().baseUrl("http://" + SERVICE_NAME).filter(lbFunction).build()
		.get().uri("/user").accept(MediaType.APPLICATION_JSON).exchange().flatMapMany(response -> {
			return response.bodyToFlux(User.class);
		});
	}

	/**
	 * feign RPC方式调用方式，最佳实践
	 */
	@RequestMapping(value = "/findUser")
	public ResponseEntity<User> queryUserByID(@RequestParam("id") String id) {
		User user = feignService.searchUser(id);
		System.out.println("user :" + user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// 使用source 发送消息
	
	@RequestMapping(method = RequestMethod.POST, path = "/send")
	public void write(@RequestBody Map<String, Object> msg) {
		// @RequestBody 请求需要content 是json
		System.out.println("msg ：" + msg);
		source.output().send(MessageBuilder.withPayload(msg).build());

	}
	
	// 用asyncRestTemplate 异步调用 eureka 服务，已经由reactive替代
	@RequestMapping(value = "/users2")
	public ResponseEntity<List<User>> readUserInfo2() {

		List<User> users = asynUserService.readUserInfo();
		System.out.println("users :" + users);
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

}
