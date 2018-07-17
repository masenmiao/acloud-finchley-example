/*
 * Copyright 2012-2020 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * @author lzhoumail@126.com/zhouli
 * Git http://git.oschina.net/zhou666/spring-cloud-7simple
 */
package acloud.simple.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
//import cloud.simple.service.UserServiceProvider.FeignUserService;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import acloud.simple.service.data.User;

@Service
public class UserCallService {
	 @Autowired	 
	 RestTemplate restTemplate;
	
//	 @Autowired
//	 FeignUserService feignUserService;
	 
	 @Autowired
	 UserRemoteService remoteService;
	 
	 final String SERVICE_NAME="cloud-simple-service";
	 
	 @HystrixCommand(fallbackMethod = "fallbackSearchAll")
	 public List<User> readUserInfo() {
		 List<User> list = null;
		 try{
			 //restTemplate比较特殊，调用的服务异常时，貌似会有状态维护，
			 //此时就先触发了熔断器,后才进入catch种
			 //list = restTemplate.getForObject("http://"+SERVICE_NAME+"/user", List.class);
			 
			 list = remoteService.searchAll();
			 
			 //不需要端口
		 }catch(Exception ex){//异常没有阻止断路器
			 System.out.println("restTemplate getForObject error");
			 ex.printStackTrace();
		 }
		 
		 return list;
		 //return feignUserService.readUserInfo();
	 }	 
	 private List<User> fallbackSearchAll() {
		 System.out.println("HystrixCommand fallbackMethod handle!");
		 List<User> ls = new ArrayList<User>();
		 User user = new User();
		 user.setName("TestHystrixCommand");
		 ls.add(user);
		 return ls;
	 }
}
