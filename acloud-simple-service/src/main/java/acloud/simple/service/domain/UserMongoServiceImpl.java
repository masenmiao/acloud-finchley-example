package acloud.simple.service.domain;

import acloud.simple.service.dao.UserDao;
import acloud.simple.service.dao.UserRepository;
import acloud.simple.service.data.User;
import acloud.simple.service.spe.UserReactiveService;
import acloud.simple.service.spe.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

/**
 * 业务实现类
 *
 * @author masen
 */
@Slf4j
//@Service
public class UserMongoServiceImpl implements UserReactiveService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private UserDao userMapper;

    @Override
    public Flux<User> searchAll() {
        return userRepository.findAll();
    }

    @PostConstruct
    //模拟数据
    public void initCacheUsers() {
        System.out.println("初始化 mongo users.");
        try {
            userRepository.deleteAll().subscribe();
            System.out.println("初始化 mongo users.");
            List<User> list = userMapper.findAll();
            if (CollectionUtils.isNotEmpty(list)) {
                list.stream().forEach(item -> {
                    System.out.println("item : " + item);
                });
                userRepository.insert(list).subscribe();

            }
        } catch (Exception ex) {
            log.warn("连接不上mongodb");
        }

    }

    @Override
    public Mono<User> searchUser(String id) {
        return userRepository.findById(id);

    }
}
