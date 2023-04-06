package com.schurov.ssu.web.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.schurov.ssu.web.data.model.User;
import com.schurov.ssu.web.data.model.UserNew;
import com.schurov.ssu.web.data.repositories.UserRepository;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component

public class RabbitTokenListener {
    private final UserRepository userRepository;

    public RabbitTokenListener(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RabbitHandler
    @RabbitListener(queues = "user-token")
    public void receiver(String message) throws JsonProcessingException {
        UserNew newUser = new ObjectMapper().readValue(message, UserNew.class);
        User bySso1 = userRepository.findBySso(newUser.getSso());
        boolean bySso = bySso1 == null;

        userRepository.save(new User()
                .setSso(newUser.getSso())
                .setName(bySso1 != null ? bySso1.getName() : null)
                .setToken(newUser.getToken())
                .setNev(bySso)
        );
    }
}

