package com.casestudy.service.cart;

import com.casestudy.model.Cart;
import com.casestudy.model.User;
import com.casestudy.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Override
    public Iterable<Cart> findAll() {

        return cartRepository.findAll();
    }

    @Override
    public Optional<Cart> findByCartId(Long cart_id) {
        return cartRepository.findById(cart_id);
    }

    @Override
    public void save(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public void remove(Long cart_id) {
        cartRepository.deleteById(cart_id);
    }

    @Override
    public Iterable<Cart> findAllByUser(User user) {
        return cartRepository.findAllByUser(user);
    }

    @Override
    public Iterable<Cart> findAllByOrderNumberContaining(Long orderNumber) {
        return cartRepository.findAllByOrderNumberContaining(orderNumber);
    }
}
