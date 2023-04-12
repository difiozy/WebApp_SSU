package com.schurov.ssu.web.data.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@Data
@RedisHash("User")
@Accessors(chain = true)
public class UserCacheable {
    private String id;
    private String password;
    private LocalDateTime dateTime;
}
