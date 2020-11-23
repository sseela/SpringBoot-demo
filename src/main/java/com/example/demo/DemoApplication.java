package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;



@SpringBootApplication
@ComponentScan(basePackages= {"com.example.controller", "com.example.custom.exceptions", "com.example.exception.hanlder", 
		"com.example.viewModels", "com.example.service", "com.example.swagger", "com.example.interceptor"})
@Slf4j
public class DemoApplication {

	public static void main(String[] args) {
		
		log.trace("trace");
		log.debug("debug");
		log.info("info");
		log.warn("warn");
		log.error("error");
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
