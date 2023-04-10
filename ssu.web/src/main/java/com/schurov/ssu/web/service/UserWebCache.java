package com.schurov.ssu.web.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.schurov.ssu.web.data.cache.UserCacheRepository;
import com.schurov.ssu.web.data.model.UserCacheable;
import com.schurov.ssu.web.data.model.UserWebDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Slf4j
public class UserWebCache implements UserCache {
    private final UserCacheRepository userCacheRepository;

    public UserWebCache(UserCacheRepository userCacheRepository) {
        this.userCacheRepository = userCacheRepository;
    }


    @Override
    public UserDetails getUserFromCache(String username) {

        Optional<UserCacheable> byId = userCacheRepository.findById(username);
        if (byId.isPresent()) {
            UserWebDetails userWebDetails = new UserWebDetails()
                    .setUserName(byId.get().getId())
                    .setPassword(byId.get().getPassword());
            try {
                log.info(new ObjectMapper().writeValueAsString(userWebDetails));
            } catch (JsonProcessingException ignored) {
            }
            return userWebDetails;
        }
        return null;
    }

    @Override
    public void putUserInCache(UserDetails user) {
        try {
            log.info("put user {}", new ObjectMapper().writeValueAsString(user));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        userCacheRepository.save(new UserCacheable()
                .setId(user.getUsername())
                .setPassword(user.getPassword()));
    }

    @Override
    public void removeUserFromCache(String username) {
        userCacheRepository.deleteById(username);
    }
}
