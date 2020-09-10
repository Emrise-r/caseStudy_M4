package com.casestudy;

import com.casestudy.service.cart.CartService;
import com.casestudy.service.cart.CartServiceImpl;
import com.casestudy.service.category.CategoryService;
import com.casestudy.service.category.CategoryServiceImpl;
import com.casestudy.service.product.ProductService;
import com.casestudy.service.product.ProductServiceImpl;
import com.casestudy.service.user.UserService;
import com.casestudy.service.user.UserServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class caseStudy_M4Application {
    public static void main(String[] args) {
        SpringApplication.run(caseStudy_M4Application.class, args);
    }


    @Bean
    public CartService cartService() {
        return new CartServiceImpl();
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean
    public ProductService productService() {
        return new ProductServiceImpl();
    }

    @Bean
    public CategoryService categoryService() {
        return new CategoryServiceImpl();
    }

}
