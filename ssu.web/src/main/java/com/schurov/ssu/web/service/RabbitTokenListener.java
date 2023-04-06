package com.schurov.ssu.web.service;

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
    public void receiver(UserNew newUser) {
       userRepository.save(new User()
               .setSso(newUser.getSso())
               .setToken(newUser.getToken())
       );
    }
}

