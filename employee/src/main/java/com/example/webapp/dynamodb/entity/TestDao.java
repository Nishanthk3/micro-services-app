package com.example.webapp.dynamodb.entity;

import reactor.core.publisher.Mono;

public interface TestDao {

    Mono<Void> put(TestDO users);

    Mono<TestDO> get(String id);
}