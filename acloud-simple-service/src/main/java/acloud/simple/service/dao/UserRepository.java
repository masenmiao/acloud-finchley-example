package acloud.simple.service.dao;

import acloud.simple.service.data.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface  UserRepository extends ReactiveMongoRepository<User, String> {

}
