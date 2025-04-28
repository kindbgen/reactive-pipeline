package com.kindbgen;

import com.kindbgen.entity.User;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;

/**
 * @author <a href="mailto:kindbgen@gmail.com">Kindbgen<a/>
 * @description 用户信息服务
 * @date 2025/4/24
 * @since 1.0.0
 */
public class UserLoader implements DataLoader<User> {
    public String getName() {
        return "user";
    }

    public String[] getDependencies() {
        return new String[0];
    } // 无依赖

    public Mono<User> load(Map<String, Object> ctx) {
        return Mono.fromCallable(() -> {
            System.out.println("Calling user service...");
            return new User((String) ctx.get("userId"),(String) ctx.get("favoriteProductId"));
        }).delayElement(Duration.ofMillis(100));
    }
}