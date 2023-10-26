package com.example.webapp.controller;

import com.example.webapp.redis.Cache;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.sync.RedisCommands;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/redis")
@Slf4j
public class RedisController {

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    RedisClient redisClient;
    RedisCommands<String, String> syncCommands;
    RedisAsyncCommands<String, String> asyncCommands;

    @PostConstruct
    public void init() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        syncCommands = connection.sync();
        asyncCommands = connection.async();
    }

    @GetMapping(path = "/key/{key}")
    public String getKey(@PathVariable String key) {
        return getKeyS(key);
    }

    @PostMapping(path = "/value")
    public String insertKey(@RequestBody Cache cache) {
        log.info("cache: {} {}", cache.getKey(), cache.getExpirationType());
        syncCommands.set(cache.getKey(), cache.getValue());
        if (Objects.nonNull(cache.getExpirationType())) {
            syncCommands.expire(cache.getKey(), cache.getExpirationType().getExpiration());
        }
        return getKeyS(cache.getKey());
    }

    private String getKeyS(String key) {
        return syncCommands.get(key);
    }

    @GetMapping(path = "/keyAs/{key}")
    public String getKeyAsync(@PathVariable String key) throws ExecutionException, InterruptedException {
        return getKeyAs(key);
    }

    @PostMapping(path = "/valueAs")
    public String insertKeyAsync(@RequestBody Cache cache) throws ExecutionException, InterruptedException {
        log.info("Cache Async: {} {}", cache.getKey(), cache.getExpirationType());
        asyncCommands.set(cache.getKey(), cache.getValue());
        if (Objects.nonNull(cache.getExpirationType())) {
            asyncCommands.expire(cache.getKey(), cache.getExpirationType().getExpiration());
        }
        return getKeyAs(cache.getKey());
    }

    private String getKeyAs(String key) throws InterruptedException, ExecutionException {
        RedisFuture<String> future = asyncCommands.get(key);
        return future.get();
    }


}
