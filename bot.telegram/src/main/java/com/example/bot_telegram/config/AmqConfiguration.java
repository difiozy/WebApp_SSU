package com.example.bot_telegram.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqConfiguration {

    @Bean
    public Queue myQueue() {
        return new Queue("user-token");
    }
}
