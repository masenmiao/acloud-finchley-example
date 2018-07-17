package acloud.simple.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import acloud.simple.service.data.User;
import acloud.simple.service.spe.UserService;

@FeignClient("cloud-simple-service")
public interface UserRemoteService extends UserService{
	//模拟，请求 cloud-simple-service 的 /user 服务
	@RequestMapping(method = RequestMethod.GET, value = "/user")
	List<User> searchAll() ;
	
	@RequestMapping(value="/findById",method=RequestMethod.GET)
	User findById(String id) ;
	
	//以上为测试，推荐直接依赖 api 的 接口，接口配置了 feign 请求地址，可直接调用,参考 UserController.readUserInfo222 
}
