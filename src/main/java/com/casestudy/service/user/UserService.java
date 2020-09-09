package com.casestudy.service.user;

import com.casestudy.model.User;

import java.util.Optional;

public interface UserService {
    Iterable<User> findAll();

    Optional<User> findByUserId(Long user_id);

    void save(User user);

    void remove(Long user_id);
}
