package com.test.resttemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ResttemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResttemplateApplication.class, args);
	}

}
