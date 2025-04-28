package com.kindbgen;

import com.kindbgen.entity.Product;
import com.kindbgen.entity.User;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;

/**
 * @author <a href="mailto:kindbgen@gmail.com">Kindbgen<a/>
 * @description 商品信息服务（依赖用户）
 * @date 2025/4/24
 * @since 1.0.0
 */
public class ProductLoader implements DataLoader<Product> {
    public String getName() {
        return "product";
    }

    public String[] getDependencies() {
        return new String[]{"user"};
    }

    public Mono<Product> load(Map<String, Object> ctx) {
        return Mono.just((User) ctx.get("user"))
                .flatMap(user -> Mono.fromCallable(() -> {
                    System.out.println("Calling product service...");
                    return new Product(user.getFavoriteProductId(), Math.random() * 100);
                })).delayElement(Duration.ofMillis(200));
    }
}