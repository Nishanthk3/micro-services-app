package com.example.webapp.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.cluster.ClusterClientOptions;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.resource.ClientResources;
import io.lettuce.core.resource.DefaultClientResources;
import io.lettuce.core.resource.Delay;
import io.lettuce.core.resource.DirContextDnsResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

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
     * https://lettuce.io/core/release/reference/ - 4.3.3. Examples
     * https://github.com/lettuce-io/lettuce-core/wiki/Asynchronous-API
     */
    @Bean
    RedisClient redisClient() {
//        RedisClient redisClient = RedisClient
//                .create("redis://password@localhost:6379/");
//        log.info("RedisClient : {}", redisClient);

        // Standalone Redis
        RedisClient redisClient = RedisClient.create(RedisURI.create("localhost", 6379));
        log.info("RedisClient: {}", redisClient);

        return redisClient;
    }

    @Bean
    RedisClusterClient redisClusterClient() {
        // Clustered Redis
        RedisClusterClient redisClusterClient;
        RedisURI redis0 = RedisURI.create("localhost", 7000);
        RedisURI redis1 = RedisURI.create("localhost", 7001);
        RedisURI redis2 = RedisURI.create("localhost", 7002);

        redisClusterClient = RedisClusterClient.create(getClientResources(), Arrays.asList(redis0, redis1, redis2));
        log.info("RedisClusterClient: {}", redisClusterClient);
        final ClusterClientOptions clusterClientOptions = ElastiCacheUtil.getClusterClientOptions();
        redisClusterClient.setOptions(clusterClientOptions);
        return redisClusterClient;
    }

    static ClientResources getClientResources() {
        ClientResources clientResources =
                DefaultClientResources.builder()
                        .dnsResolver(new DirContextDnsResolver())
                        .reconnectDelay(
                                Delay.fullJitter(
                                        Duration.ofMillis(100), // minimum 100 millisecond delay
                                        Duration.ofSeconds(10), // maximum 10 second delay
                                        100,
                                        TimeUnit.MILLISECONDS)) // 100 millisecond base
                        .build();
        return clientResources;
    }
}
