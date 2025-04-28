package com.kindbgen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author <a href="mailto:kindbgen@gmail.com">Kindbgen<a/>
 * @description 用户实体（被ProductLoader依赖）
 * @date 2025/4/24
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
public class User {
    private String id;          // 用户ID（与ctx中的"userId"对应）
    private String favoriteProductId; // 用户收藏的商品ID（ProductLoader中使用）
    public User(String id) { this.id = id; }

}