package com.kindbgen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author <a href="mailto:kindbgen@gmail.com">Kindbgen<a/>
 * @description 库存实体（被ShippingLoader依赖）
 * @date 2025/4/24
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
public class Inventory {
    private String productId;   // 商品ID（与Product关联）
    private String warehouseId; // 仓库ID（ShippingFee计算用）
    public Inventory(String productId) { this.productId = productId; }

}
