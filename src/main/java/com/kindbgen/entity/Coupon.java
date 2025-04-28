package com.kindbgen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author <a href="mailto:kindbgen@gmail.com">Kindbgen<a/>
 * @description 优惠券实体（依赖User和Product）
 * @date 2025/4/24
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
public class Coupon {
    private String userId;      // 用户ID（与User关联）
    private String productId;   // 适用商品ID（与Product关联）
}
