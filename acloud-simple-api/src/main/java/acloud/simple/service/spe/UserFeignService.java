package acloud.simple.service.spe;


import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import acloud.simple.service.data.User;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
/**
 * feign 接口定义 ， 接口由service-simple实现，web-simple调用
 * @author think
 *
 */

@FeignClient("cloud-simple-service")
public interface UserFeignService {
//	@ApiOperation(value="查询全部用户",response = User.class,responseContainer = "List")
	//response,responseContainer没有生效
	@RequestMapping(method = RequestMethod.GET, value = "/searchAll")
	public List<User> searchAll();
	
	
	
	//多个参数，需要用 @RequestParam注解标注
//	@ApiOperation(value="查询用户2通过id",response = User.class)
//    @ApiImplicitParams(
//            {
//                    @ApiImplicitParam(name = "id", value = "用户", required = true, dataType = "String" ,paramType="query"),
//            }
//    )
//    @ApiResponses(
//            {
//                    @ApiResponse(code = 200, message = "Successful — 请求已完成"),
//                    @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
//                    @ApiResponse(code = 401, message = "未授权客户机访问数据"),
//                    @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
//                    @ApiResponse(code = 500, message = "服务器不能完成请求")
//            }
//    )
	
	@RequestMapping(method = RequestMethod.GET, value = "/feignUser")
	public User searchUser(
			@RequestParam("id") String id);
}
