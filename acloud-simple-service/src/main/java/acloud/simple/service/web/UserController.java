package acloud.simple.service.web;

import java.util.List;

import acloud.simple.service.spe.UserReactiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import acloud.simple.service.data.User;
import acloud.simple.service.spe.UserService;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * doc 说明：
@Api：用在类上，说明该类的作用
@ApiOperation：用在方法上，说明方法的作用
@ApiImplicitParams：用在方法上包含一组参数说明
@ApiImplicitParam：用在@ApiImplicitParams注解中，指定一个请求参数的各个方面
paramType：参数放在哪个地方
header-->请求参数的获取：@RequestHeader
query-->请求参数的获取：@RequestParam
path（用于restful接口）-->请求参数的获取：@PathVariable
body（不常用）
form（不常用）
name：参数名
dataType：参数类型
required：参数是否必须传
value：参数的意思
defaultValue：参数的默认值
@ApiResponses：用于表示一组响应
@ApiResponse：用在@ApiResponses中，一般用于表达一个错误的响应信息
code：数字，例如400
message：信息，例如"请求参数没填好"
response：抛出异常的类
@ApiModel：描述一个Model的信息（这种一般用在post创建的时候，使用@RequestBody这样的场景，请求参数无法使用@ApiImplicitParam注解进行描述的时候）
@ApiModelProperty：描述一个model的属性
 * @author masen
 *
 */
@ConfigurationProperties("my")
@RestController
public class UserController {

	@Resource(name = "userServiceImpl")
	UserService userService;

	@Resource(name = "userRedisServiceImpl")
	UserReactiveService redisService;

//	@Resource(name = "userMongoServiceImpl")
//	UserReactiveService mongoService;
	//远程配置
	@Value("${my.message}")
	private String messageString;

	private String message;

	public void setMessage(String message) {
		this.message = message;
	}

	@GetMapping(value="/user")
	public List<User> searchAll(){
		System.out.println("message config is:"+messageString);
		System.out.println("message dany is:"+message);
		List<User> ls=userService.searchAll();
		return ls;
	}

	//produces = MediaType.APPLICATION_STREAM_JSON_VALUE 一个一个返回，前端一个一个展示
	@GetMapping(value="/userRedis",produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<User> searchAllRedis(){
		return redisService.searchAll();
	}
//	@GetMapping(value="/userMongo",produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
//	public Flux<User> searchAllMongo(){
//		return mongoService.searchAll();
//	}


//	@ApiOperation("查询用户通过ID")
//    @ApiImplicitParams(
//            {
//                    @ApiImplicitParam(name = "id", value = "用户", required = true, dataType = "String", paramType="query"),
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

	@GetMapping(value="/findById")
	public User findById(@RequestParam("id") String id){
		System.out.println("message id is:"+id);
		System.out.println("message dany is:"+message);
		User user = userService.findById(id);
		System.out.println("user : "+ user);
		return user;
	}

	@GetMapping(value="/findByIdRedis")
	public Mono<User> findByIdRedis(@RequestParam("id") String id){
		System.out.println("message id is:"+id);
		return redisService.searchUser(id);
	}
//	@GetMapping(value="/findByIdMongo")
//	public Mono<User> findByIdMongo(@RequestParam("id") String id){
//		System.out.println("message id is:"+id);
//		return mongoService.searchUser(id);
//	}
}
