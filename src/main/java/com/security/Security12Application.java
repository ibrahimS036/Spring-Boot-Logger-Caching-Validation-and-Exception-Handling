package com.security;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class Security12Application {

    public static void main(String[] args) {
        SpringApplication.run(Security12Application.class, args);
    }

    @Bean
    public ModelMapper mapper() {

        return new ModelMapper();
    }

}
