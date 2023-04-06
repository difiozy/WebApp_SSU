package com.schurov.ssu.web.controllers;

import com.schurov.ssu.web.data.model.User;
import com.schurov.ssu.web.data.repositories.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/{sso}")
    public User getUserBySso(@PathVariable("sso") String sso) {
        return userRepository.findBySso(sso);
    }

    @GetMapping("/ok")
    public String ok() {
        return "ok";
    }
}
