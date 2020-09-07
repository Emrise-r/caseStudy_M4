package com.casestudy.service.user;

import com.casestudy.model.User;
import com.casestudy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUserId(Long user_id) {
        return userRepository.findOne(user_id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void remove(Long user_id) {
        userRepository.delete(user_id);
    }
}