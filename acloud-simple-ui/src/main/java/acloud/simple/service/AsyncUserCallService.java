/*
 * Copyright 2012-2020 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * @author lzhoumail@126.com/zhouli
 * Git http://git.oschina.net/zhou666/spring-cloud-7simple
 */
package acloud.simple.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import acloud.simple.service.data.User;

/**
 * spring 5 使用 WebClient 替代 asyncRestTemplate
 * @author masen
 *
 */
@Service
public class AsyncUserCallService {
	@Autowired
	AsyncRestTemplate asyncRestTemplate;
	final String SERVICE_NAME = "cloud-simple-service";

	
	public List<User> readUserInfo() {
		List<User> users=new ArrayList();;
		
		//users = asyncRestTemplate.("http://"+SERVICE_NAME+"/user", List.class);
		
		//这里异步不需要返回值，这么写仅是测试 future（或有多个调用为异步时）
		ListenableFuture<ResponseEntity<List>> future = (ListenableFuture<ResponseEntity<List>>) asyncRestTemplate.getForEntity("http://"+SERVICE_NAME+"/user", List.class);
        //为啥没有getForObject ? 
		
        
        
        //future.addCallback 放在 synchronized块中，因为如果callback执行的快于当前行呢?
        try {
        	synchronized(users){
        		future.addCallback(new MyListenableFutureCallback(users));
        		users.wait();
        	}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //阻塞还可以用如下方式
//        while(true) {
//    		if(future.isDone() && future2.isDone() && future3.isDone()) {
//    			// 三个任务都调用完成，退出循环等待
//    			break;
//    		}
//    	}
        return users;
	}
	

//spring 应用内部调用可以用 Async标注，springBoot主程序要增加@EnableAsync
//@Async
//public Future<String> doTaskOne() throws Exception {
//    System.out.println("开始做任务一");
//    long start = System.currentTimeMillis();
//    Thread.sleep(random.nextInt(10000));
//    long end = System.currentTimeMillis();
//    System.out.println("完成任务一，耗时：" + (end - start) + "毫秒");
//    return new AsyncResult<>("任务一完成");
//}
/**
 * 
 * <!-- 缺省的异步任务线程池 -->   
<task:annotation-driven executor="asyncExecutor" />  
<task:executor id="asyncExecutor" pool-size="100-10000" queue-capacity="10" />  
  
<!-- 处理log的线程池 -->  
<task:executor id="logExecutor" pool-size="15-1000" queue-capacity="5" keep-alive="5"/>
 *
 *使用 ： @Async("logExecutor")  
 */
	
	/**
	 * asyncRestTemplate 使用的异步线程池 名称是否时 asyncTaskExecutor?
	 * @author think
	 *
	 */
	
	class MyListenableFutureCallback implements ListenableFutureCallback {

		Object obj;

		public MyListenableFutureCallback(Object obj) {
			this.obj = obj;
		}

		@Override
		public void onSuccess(Object result) {
			System.out.println("======client get result : " + result);

			CollectionUtils.addAll((Collection) obj, ((ResponseEntity<List>)result).getBody().iterator());
			synchronized (obj) {
				obj.notify();
			}

		}

		@Override
		public void onFailure(Throwable t) {
			System.out.println("======client get result : " + t);
			synchronized (obj) {
				obj.notify();
			}
		}
	}
}
