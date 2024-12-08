package com.example.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.project.service.impl.ManagerServiceImpl;


@SpringBootApplication
public class WebFinalTermApplication {

	public static void main(String[] args) {
		 ConfigurableApplicationContext context = SpringApplication.run(WebFinalTermApplication.class, args);
	
	}

}
