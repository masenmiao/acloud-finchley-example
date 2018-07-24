/*
 * Copyright 2012-2020 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * @author lzhoumail@126.com/zhouli
 * Git http://git.oschina.net/zhou666/spring-cloud-7simple
 */

package acloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;


import feign.Request;

//@SpringBootApplication
////@EnableEurekaClient
//@EnableHystrix
//@EnableCircuitBreaker
//@EnableFeignClients

//并打开 http://localhost:port/hystrix 来访问我们的控制面板，输入 http://localhost:port/hystrix.stream 来监控我们的访问(待测试)
@SpringCloudApplication
@EnableFeignClients
public class SimpleUIBootstrap {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SimpleUIBootstrap.class, args);
	}
	
	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	//非阻塞异步调用
	@LoadBalanced
	@Bean
	AsyncRestTemplate asyncRestTemplate() {
		return new AsyncRestTemplate();
	}
	

	
//	@Bean
//	@Scope("prototype")
//	public Feign.Builder feignBuilder() {
//		return Feign.builder();
//	}
//
//	@Bean
//	public Logger.Level feignLogger() {
//		return Logger.Level.FULL;
//	}
//
	private static final int FIVE_SECONDS = 5000;

	@Bean
	public Request.Options options() {
		return new Request.Options(FIVE_SECONDS, FIVE_SECONDS);
	}

}
