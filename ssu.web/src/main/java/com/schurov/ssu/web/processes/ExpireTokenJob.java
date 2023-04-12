package com.schurov.ssu.web.processes;

import com.schurov.ssu.web.data.cache.UserCacheRepository;
import com.schurov.ssu.web.data.repositories.UserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExpireTokenJob {

    private final UserRepository userRepository;
    private final UserCacheRepository userCache;

    public ExpireTokenJob(UserRepository userRepository, UserCacheRepository userCache) {
        this.userRepository = userRepository;
        this.userCache = userCache;
    }

    @Scheduled(fixedDelay = 1000)
    public void deleteExpireTokens() {
        List<String> expireTokenSso = userRepository.getExpireTokenSso();
        if (expireTokenSso.isEmpty()) {
            return;
        }
        userRepository.updateUsersBySso(expireTokenSso);
        userCache.deleteAllById(expireTokenSso);
    }
}
