package com.kindbgen;

import com.kindbgen.entity.Inventory;
import com.kindbgen.entity.Product;
import com.kindbgen.entity.ShippingFee;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;

/**
 * @author <a href="mailto:kindbgen@gmail.com">Kindbgen<a/>
 * @description 运费服务（依赖商品、库存）
 * @date 2025/4/24
 * @since 1.0.0
 */
public class ShippingLoader implements DataLoader<ShippingFee> {
    public String getName() {
        return "shipping";
    }

    public String[] getDependencies() {
        return new String[]{"product", "inventory"};
    }

    public Mono<ShippingFee> load(Map<String, Object> ctx) {
        return Mono.zip(
                Mono.just((Product) ctx.get("product")),
                Mono.just((Inventory) ctx.get("inventory"))
        ).flatMap(tuple -> Mono.fromCallable(() -> {
            System.out.println("Calling shipping service...");
            return new ShippingFee(tuple.getT1().getWeight(), tuple.getT2().getWarehouseId());
        })).delayElement(Duration.ofMillis(180));
    }
}