package com.casestudy;

import com.casestudy.config.StorageProperties;
import com.casestudy.service.uploadfile.StorageService;
import com.casestudy.service.cart.CartService;
import com.casestudy.service.cart.CartServiceImpl;
import com.casestudy.service.category.CategoryService;
import com.casestudy.service.category.CategoryServiceImpl;
import com.casestudy.service.product.ProductService;
import com.casestudy.service.product.ProductServiceImpl;
import com.casestudy.service.user.UserService;
import com.casestudy.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class caseStudy_M4Application implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(caseStudy_M4Application.class, args);
    }

    @Autowired
    Environment evn;

//    @Bean
//    public CartService cartService() {
//        return new CartServiceImpl();
//    }
//
//    @Bean
//    public UserService userService() {
//        return new UserServiceImpl();
//    }
//
//    @Bean
//    public ProductService productService() {
//        return new ProductServiceImpl();
//    }
//
//    @Bean
//    public CategoryService categoryService() {
//        return new CategoryServiceImpl();
//    }

    @Bean
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        return new Filter[]{filter};
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.deleteAll();
            storageService.init();
        };
    }
}
