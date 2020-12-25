package sterben.scheduling.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sterben.scheduling.demo.entity.User;

public interface UserRepository extends MongoRepository<User, Long> {
    User findByUsername(String username);

    User findById(long id);
    //List<User> findAll(Pageable pageable);

    //List<User> findAllUser();

    //User findUserById(long id);
}
