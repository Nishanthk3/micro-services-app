package com.example.webapp.redis;

import lombok.Getter;

public enum CacheExpirationType {

    cacheSec(1),
    cacheMinute(60),
    cacheHalfHour(1800),
    cacheHour(3600),
    cacheDay(86400);

    @Getter
    private int expiration;

    CacheExpirationType(int expiration) {
        this.expiration = expiration;
    }
}
