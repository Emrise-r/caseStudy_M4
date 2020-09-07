package com.casestudy.service.user;

import com.casestudy.model.User;

public interface UserService {
    Iterable<User> findAll();

    User findByUserId(Long user_id);

    void save(User user);

    void remove(Long user_id);
}
