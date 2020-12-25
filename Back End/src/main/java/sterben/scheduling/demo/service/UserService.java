package sterben.scheduling.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import sterben.scheduling.demo.entity.User;

import java.util.List;

@Service
public interface UserService {

    List<User> getAllUser();

    Page<User> findAllUser(Integer page, Integer size);

    User getUserById(long id);

    void update(long id, User userUpdate);

    void remove(long id);

    void createUser(User user);

}
