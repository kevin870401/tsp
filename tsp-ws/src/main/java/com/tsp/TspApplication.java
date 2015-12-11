package com.tsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class TspApplication {

	public static void main(String[] args) {
		SpringApplication.run(TspApplication.class, args);
	}

}
