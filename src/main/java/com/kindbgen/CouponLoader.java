package com.kindbgen;

import com.kindbgen.entity.Coupon;
import com.kindbgen.entity.Product;
import com.kindbgen.entity.User;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;

/**
 * @author <a href="mailto:kindbgen@gmail.com">Kindbgen<a/>
 * @description 优惠券服务（依赖用户、商品
 * @date 2025/4/24
 * @since 1.0.0
 */
public class CouponLoader implements DataLoader<Coupon> {
    public String getName() {
        return "coupon";
    }

    public String[] getDependencies() {
        return new String[]{"user", "product"};
    }

    public Mono<Coupon> load(Map<String, Object> ctx) {
        return Mono.zip(
                Mono.just((User) ctx.get("user")),
                Mono.just((Product) ctx.get("product"))
        ).flatMap(tuple -> Mono.fromCallable(() -> {
            System.out.println("Calling coupon service...");
            return new Coupon(tuple.getT1().getId(), tuple.getT2().getId());
        })).delayElement(Duration.ofMillis(120));
    }
}