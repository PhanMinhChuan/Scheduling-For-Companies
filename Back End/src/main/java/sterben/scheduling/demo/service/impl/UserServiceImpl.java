package sterben.scheduling.demo.service.impl;

import org.h2.util.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sterben.scheduling.demo.entity.CustomUserDetails;
import sterben.scheduling.demo.entity.User;
import sterben.scheduling.demo.repository.UserRepository;
import sterben.scheduling.demo.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> findAllUser(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return (Page<User>) userRepository.findAll(pageable);
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public void update(long id, User userUpdate) {
        User user = userRepository.findById(id);
        //user.set(userUpdate.getPassword());
        user.setIdJob(userUpdate.getIdJob());
        userRepository.save(user);
    }

    @Override
    public void remove(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void createUser(User user) {
        User userEx = new User();
        if (user.getUsername() != null && user.getPassword() != null) {
            userEx.setId(userRepository.findAll().get(userRepository.findAll().size() - 1).getId() + 1);
            userEx.setUsername(user.getUsername());
            userEx.setPassword(passwordEncoder.encode(user.getPassword()));
            userEx.setIdJob(user.getIdJob());
            userEx.setRole("ROLE_STAFF");
            userRepository.save(userEx);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user.getId(), user.getUsername(), user.getPassword(), user.getRole());
    }

    @Transactional
    public UserDetails loadUserById(int id) {
        User user = userRepository.findById(id);
        return new CustomUserDetails(user.getId(), user.getUsername(), user.getPassword(), user.getRole());
    }

}
