package com.github.sailboat.notes.demos.vo;

import lombok.Data;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Data
public class ProductInfoQueryVO {
    // 主键
    private Integer productId;

    // 商品名称
    private String productName;

    // 商品价格
    @Min(value = 0, message = "商品价格不允许为负数")
    private BigDecimal productPrice;

    // 描述
    private String productDescription;

    // 上架状态
    private Integer productStatus;
}
