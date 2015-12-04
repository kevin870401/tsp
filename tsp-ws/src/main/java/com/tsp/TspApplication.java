package com.tsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan({"com.tsp"})
public class TspApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(TspApplication.class, args);
    }
}
