package com.itau.api.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.itau.api.controller"})
public class ItauApiTodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItauApiTodoApplication.class, args);
	}

}
