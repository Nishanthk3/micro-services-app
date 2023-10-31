package com.example.webapp.controller;

import com.example.webapp.redis.Cache;
import com.example.webapp.redis.Campaign;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.codec.ByteArrayCodec;
import io.lettuce.core.codec.RedisCodec;
import io.lettuce.core.codec.StringCodec;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
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
    RedisCommands<String, byte[]> syncByteArrayCommands;
    RedisCodec<String, byte[]> codec = RedisCodec.of(new StringCodec(), new ByteArrayCodec());

    @PostConstruct
    public void init() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        syncCommands = connection.sync();
        asyncCommands = connection.async();

        StatefulRedisConnection<String, byte[]> connection1 = redisClient.connect(codec);
        syncByteArrayCommands = connection1.sync();
    }

    @GetMapping(path = "/keys")
    public List<String> getKeys() {
        return syncCommands.keys("*");
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

    @GetMapping(path = "/serialized/{key}")
    public Campaign get(@PathVariable String key) {
        byte[] bytes = syncByteArrayCommands.get(key);
        Campaign campaign = SerializationUtils.deserialize(bytes);
        return campaign;
    }

    @PostMapping(path = "/serialized", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String addCampaign(@RequestBody Campaign campaign) {
        log.info("Campaign: " + campaign.toString());
        String uuid = UUID.randomUUID().toString();
        syncByteArrayCommands.set(uuid, SerializationUtils.serialize(campaign));
        return uuid;
    }
}
