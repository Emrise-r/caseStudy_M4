package com.casestudy.service.cart;

import com.casestudy.model.Cart;
import com.casestudy.model.User;


public interface CartService {

    Iterable<Cart> findAll();

    Cart findByCartId(Long cart_id);

    void save(Cart cart);

    void remove(Long cart_id);

    Iterable<Cart> findAllByUser(User user);

    Iterable<Cart> findAllByOrderNumberContaining(Long orderNumber);
}
