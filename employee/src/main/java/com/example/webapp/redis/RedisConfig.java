package com.example.webapp.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@Slf4j
public class RedisConfig {

    @Bean
    LettuceConnectionFactory lettuceConnectionFactory() {
        return new LettuceConnectionFactory();
    }

    @Bean
    RedisTemplate redisTemplate() {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory());
        log.info("RedisTemplate: {}", redisTemplate);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }

    /**
     * https://lettuce.io/core/release/reference/ 4.3.3. Examples
     */
    @Bean
    RedisClient redisClient() {
//        RedisClient redisClient = RedisClient
//                .create("redis://password@localhost:6379/");
//        log.info("RedisClient : {}", redisClient);

        RedisClient redisClient = RedisClient.create(RedisURI.create("localhost", 6379));
        log.info("RedisClient with Redis URI: {}", redisClient);

        return redisClient;
    }
}
