package com.kindbgen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author <a href="mailto:kindbgen@gmail.com">Kindbgen<a/>
 * @description 商品实体（被InventoryLoader、CouponLoader、ShippingLoader依赖）
 * @date 2025/4/24
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
public class Product {
    private String id;          // 商品ID
    private double weight;      // 商品重量（ShippingFee计算用）
    public Product(String id) { this.id = id; }

}
