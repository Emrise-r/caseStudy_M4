package com.casestudy.repository;

import com.casestudy.model.Cart;
import com.casestudy.model.User;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, Long> {
    Iterable<Cart> findAllByUser(User user);

    Iterable<Cart> findAllByOrderNumberContaining(Long orderNumber);
}
