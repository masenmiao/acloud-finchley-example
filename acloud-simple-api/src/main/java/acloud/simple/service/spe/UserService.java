package acloud.simple.service.spe;


import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import acloud.simple.service.data.User;


public interface UserService {


	public List<User> searchAll();
	
	public User findById(String id);
}
