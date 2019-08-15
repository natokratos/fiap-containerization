package com.itau.api.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.itau.api.service, com.itau.api.controller"})
@EntityScan(basePackages={"com.itau.api.entity"})
@EnableJpaRepositories(basePackages = {"com.itau.api.repository"})
public class ItauApiTodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItauApiTodoApplication.class, args);
	}

}
