package org.acme.service;


import io.quarkus.redis.client.reactive.ReactiveRedisClient;
import io.smallrye.mutiny.Uni;
import io.vertx.redis.client.impl.RedisClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Arrays;

@ApplicationScoped
public class CacheService {

    @Inject
    ReactiveRedisClient reactiveRedisClient;



    public Uni<Void> setReactive(String key, String value) {
        return reactiveRedisClient.set(Arrays.asList(key, value))
                .map(response -> null);
    }

    public Uni<String> getReactive(String key) {
        return reactiveRedisClient.get(key)
                .map(response -> response != null ? response.toString() : null);
    }
}