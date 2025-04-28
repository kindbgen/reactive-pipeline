package com.kindbgen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author <a href="mailto:kindbgen@gmail.com">Kindbgen<a/>
 * @description 运费实体（依赖Product和Inventory)
 * @date 2025/4/24
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
public class ShippingFee {
    private double productWeight; // 商品重量（来自Product）
    private String warehouseId;  // 仓库ID（来自Inventory）
}