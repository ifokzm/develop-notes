package com.github.sailboat.notes.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 库存
 * </p>
 *
 * @author 狂神说
 * @since 2023-08-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value="WmsStock对象", autoResultMap = true)
public class WmsStock implements Serializable {

    private static final long serialVersionUID=1L;

    @TableField(value = "主键")
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @TableField(value = "维度id")
    private Long dimId;

    @TableField(value = "仓库id")
    private Long warehouseId;

    @TableField(value = "商品sku编码")
    private String skuCode;

    @TableField(value = "sku名称")
    private String skuName;

    @TableField(value = "总量")
    private Integer totalAmount;

    @TableField(value = "可用数")
    private Integer availableAmount;

    @TableField(value = "锁定数")
    private Integer lockedAmount;

    @TableField(value = "待租数")
    private Integer forRentAmount;

    @TableField(value = "待进场数")
    private Integer pendingAmount;

    @TableField(value = "在租数")
    private Integer rentingAmount;

    @TableField(value = "维修数")
    private Integer maintainAmount;

    @TableField(value = "报停数")
    private Integer suspendAmount;

    @TableField(value = "退场数")
    private Integer exitingAmount;

    @TableField(value = "整备数")
    private Integer preparingAmount;

    @TableField(value = "拆件数")
    private Integer splitAmount;

    @TableField(value = "调出数;(调出)")
    private Integer allotOutAmount;

    @TableField(value = "调入数;")
    private Integer allotInAmount;

    @TableField(value = "删除数")
    private Integer deleteAmount;

    @TableField(value = "启用标识;0未启用，1启用（默认)")
    private Boolean enableFlag;

    @TableField(value = "扩展信息")
    private String extra;

    @TableField(value = "备注")
    private String remark;

    @TableField(value = "版本号")
    @Version
    private Integer version;

    @TableField(value = "删除标识;0未删除(默认)，1删除")
    @TableLogic
    private Boolean deleted;

    @TableField(value = "租户id")
    private String tenantId;

    @TableField(value = "创建人")
    private Long createdBy;

    @TableField(value = "创建时间")
    private Date createdAt;

    @TableField(value = "更新人")
    private Long updatedBy;

    @TableField(value = "更新时间")
    private Date updatedAt;

    @TableField(value = "apaas实体标识")
    private Long objectId;


}
