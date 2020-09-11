package com.casestudy;

import com.casestudy.config.StorageProperties;
import com.casestudy.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.Filter;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class caseStudy_M4Application extends WebMvcConfigurerAdapter {
    public static void main(String[] args) {
        SpringApplication.run(caseStudy_M4Application.class, args);
    }

    @Autowired
    Environment env;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String fileUpload = env.getProperty("file_upload").toString();

        // Image resource.
        registry.addResourceHandler("/i/**") //
                .addResourceLocations("file:" + fileUpload);
    }
}
