package com.casestudy.repository;

import com.casestudy.model.Role;
import com.casestudy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String username);

    Iterable<User> findAllByRole(Role role);

}