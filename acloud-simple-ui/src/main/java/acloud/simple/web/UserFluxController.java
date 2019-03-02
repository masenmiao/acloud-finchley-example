/*
 * Copyright 2012-2020 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * @author lzhoumail@126.com/zhouli
 * Git http://git.oschina.net/zhou666/spring-cloud-7simple
 */

package acloud.simple.web;

import acloud.simple.service.data.User;
import acloud.simple.service.spe.UserFeignService;
import acloud.simple.service.spe.UserReactiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancerExchangeFilterFunction;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class UserFluxController {


	@Autowired
	UserReactiveService reactiveService;



	/**
	 * 问题：Openfeign  还不支持反应式
	 * feign该版本不支持reactive ,
	 * 参考：https://github.com/OpenFeign/feign/tree/master/reactive
	 */

	@GetMapping(value="/fluxFindAll")
	public Flux<User> fluxFindAll(){
		return reactiveService.searchAll();
	}
	@GetMapping(value="/fluxFindById")
	public Mono<User> fluxFindById(@RequestParam("id") String id){
		System.out.println("message id is:"+id);
		return reactiveService.searchUser(id);
	}

}
