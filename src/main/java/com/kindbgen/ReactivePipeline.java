package com.kindbgen;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author <a href="mailto:kindbgen@gmail.com">Kindbgen<a/>
 * @description 执行引擎实现
 * @date 2025/4/24
 * @since 1.0.0
 */
public class ReactivePipeline {
    private final Map<String, DataLoader<?>> loaderMap;
    private final ReactiveCache cache = new ReactiveCache();
    private final List<String> executionOrder;

    public ReactivePipeline(Map<String, DataLoader<?>> loaders) {
        this.loaderMap = loaders;
        this.executionOrder = DagScheduler.schedule(loaders);
    }

    public Mono<Map<String, Object>> execute(Map<String, Object> initialContext) {
        Map<String, Object> context = new ConcurrentHashMap<>(initialContext);

        return Flux.fromIterable(executionOrder)
                .flatMap(loaderName -> {
                    DataLoader<?> loader = loaderMap.get(loaderName);
                    return cache.computeIfAbsent(loaderName, () ->
                            resolveDependencies(loader, context)
                                    .then(Mono.defer(() -> loader.load(context)))
                    );
                })
                .then(Mono.fromCallable(() -> context));
    }

    private Mono<Void> resolveDependencies(DataLoader<?> loader, Map<String, Object> context) {
        return Flux.fromArray(loader.getDependencies())
                .flatMap(depName -> cache.get(depName)
                        .doOnNext(result -> context.put(depName, result))
                )
                .then();
    }
}
