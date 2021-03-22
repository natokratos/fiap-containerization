package com.fiap.api.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.fiap.api.service, com.fiap.api.controller"})
@EntityScan(basePackages={"com.fiap.api.domain"})
@EnableJpaRepositories(basePackages = {"com.fiap.api.repository"})
public class OrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

}
