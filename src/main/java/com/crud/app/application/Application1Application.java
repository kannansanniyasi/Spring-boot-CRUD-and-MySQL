package com.crud.app.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages={"com.crud.app"})
@EntityScan("com.crud.app.entity")
@EnableJpaRepositories("com.crud.app.repository")
public class Application1Application {

	public static void main(String[] args) {
		SpringApplication.run(Application1Application.class, args);
	}

}
