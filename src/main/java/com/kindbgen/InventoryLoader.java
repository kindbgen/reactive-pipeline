package com.kindbgen;

import com.kindbgen.entity.Inventory;
import com.kindbgen.entity.Product;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;

/**
 * @author <a href="mailto:kindbgen@gmail.com">Kindbgen<a/>
 * @description 库存服务（依赖商品）
 * @date 2025/4/24
 * @since 1.0.0
 */
public class InventoryLoader implements DataLoader<Inventory> {
    public String getName() {
        return "inventory";
    }

    public String[] getDependencies() {
        return new String[]{"product"};
    }

    public Mono<Inventory> load(Map<String, Object> ctx) {
        return Mono.just((Product) ctx.get("product"))
                .flatMap(product -> Mono.fromCallable(() -> {
                    System.out.println("Calling inventory service...");
                    return new Inventory(product.getId(),  "warehouse-1");
                })).delayElement(Duration.ofMillis(150));
    }
}
