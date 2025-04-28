package com.kindbgen;

import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @author <a href="mailto:kindbgen@gmail.com">Kindbgen<a/>
 * @description
 * @date 2025/4/24
 * @since 1.0.0
 */
public interface DataLoader<T> {
    String getName(); // 唯一标识
    String[] getDependencies(); // 依赖的DataLoader名称
    Mono<T> load(Map<String, Object> context); // 业务加载方法
}