package com.kindbgen;

import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

/**
 * @author <a href="mailto:kindbgen@gmail.com">Kindbgen<a/>
 * @description
 * @date 2025/4/24
 * @since 1.0.0
 */
public class ReactiveCache {
    private final Map<String, Mono<?>> cache = new ConcurrentHashMap<>();

    public Mono<Object> get(String key) {
        return Mono.defer(() -> (Mono<Object>) cache.getOrDefault(key, Mono.empty()));
    }

    public <T> Mono<T> computeIfAbsent(String key, Supplier<Mono<T>> supplier) {
        return Mono.defer(() -> {
            Mono<T> mono = (Mono<T>) cache.computeIfAbsent(key, k ->
                    supplier.get().cache()
            );
            return mono;
        });
    }
}
