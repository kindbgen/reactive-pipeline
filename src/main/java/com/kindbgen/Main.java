package com.kindbgen;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:kindbgen@gmail.com">Kindbgen<a/>
 * @description
 * @date 2025/4/24
 * @since 1.0.0
 */
public class Main {
    public static void main(String[] args) {
        Map<String, DataLoader<?>> loaders = new HashMap<>();
        loaders.put("user", new UserLoader());
        loaders.put("product", new ProductLoader());
        loaders.put("inventory", new InventoryLoader());
        loaders.put("coupon", new CouponLoader());
        loaders.put("shipping", new ShippingLoader());

        ReactivePipeline pipeline = new ReactivePipeline(loaders);

        Map<String, Object> context = new HashMap<>();
        context.put("userId", "12345");
        context.put("favoriteProductId", "444444");

        pipeline.execute(context)
                .doOnNext(res -> {
                    System.out.println("\nFinal Result:");
                    res.forEach((k, v) -> System.out.println(k + " -> " + v));
                })
                .block();
    }
}
