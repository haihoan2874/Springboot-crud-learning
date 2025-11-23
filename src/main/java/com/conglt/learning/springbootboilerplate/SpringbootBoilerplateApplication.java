package com.conglt.learning.springbootboilerplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.conglt.learning.springbootboilerplate.repository")
@EnableAspectJAutoProxy
public class SpringbootBoilerplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootBoilerplateApplication.class, args);
    }

}
