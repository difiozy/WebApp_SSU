package com.example.bot_telegram.rabit;

import com.example.bot_telegram.bot.TelegramBot;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitService {
    private final RabbitTemplate template;
    private final Queue queue;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public RabbitService(RabbitTemplate template, Queue queue) {
        this.template = template;
        this.queue = queue;
    }


    public void userToken(TelegramBot.UserNew user) {
        try {
            template.convertAndSend(queue.getName(), objectMapper.writeValueAsString(user));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
