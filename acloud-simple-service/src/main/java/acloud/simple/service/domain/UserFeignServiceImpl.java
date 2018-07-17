package acloud.simple.service.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import acloud.simple.service.dao.UserDao;
import acloud.simple.service.data.User;
import acloud.simple.service.spe.UserFeignService;


/**
 * feign rpc实现
 * 其它模块通过 依赖接口 feign 调用
 * @author masen
 *
 */
@Service
@RestController
@RefreshScope

public class UserFeignServiceImpl implements UserFeignService {

	@Autowired
	private UserDao userMapper;

	@Override
	public List<User> searchAll(){
		List<User> list = userMapper.findAll();
		return list;
	}
	@Override
	public User searchUser(
			@RequestParam("id") String id) {
		System.out.println("id : " + id);
		User user = userMapper.findById(id);
		return user;
	}
}
