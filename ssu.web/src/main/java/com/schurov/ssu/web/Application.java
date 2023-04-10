package com.schurov.ssu.web;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableRabbit
@EnableJdbcRepositories
@EnableRedisRepositories("com.schurov.ssu.web.data.cache")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
