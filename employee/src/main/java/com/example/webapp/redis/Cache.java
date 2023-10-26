package com.example.webapp.redis;

import lombok.*;

@Setter
@Getter
@EqualsAndHashCode
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cache {
    private String key;
    private String value;
    private CacheExpirationType expirationType;
}
