package acloud.simple.service.spe;


import acloud.simple.service.data.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@FeignClient("cloud-simple-service")
public interface UserReactiveService {

	@RequestMapping(method = RequestMethod.GET, value = "/fluxSearchAll")
	public Flux<User> searchAll();



	@RequestMapping(method = RequestMethod.GET, value = "/fluxSearchUser")
	public Mono<User> searchUser(
            @RequestParam("id") String id);
}
