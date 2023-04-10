package com.schurov.ssu.web.service;

import com.schurov.ssu.web.data.model.User;
import com.schurov.ssu.web.data.model.UserWebDetails;
import com.schurov.ssu.web.data.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserWebService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserWebCache cache;

    public UserWebService(UserRepository userRepository, UserWebCache cache) {
        this.userRepository = userRepository;
        this.cache = cache;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userFromCache = cache.getUserFromCache(username);
        if (userFromCache != null) {
            return userFromCache;
        }
        User bySso = userRepository.findBySso(username);
        if (bySso == null) {
            throw new UsernameNotFoundException("User not found");
        }
        cache.putUserInCache(new UserWebDetails()
                .setUserName(bySso.getSso())
                .setPassword(bySso.getToken()));
        return new UserWebDetails().setUserName(bySso.getSso()).setPassword(bySso.getToken());
    }
}
