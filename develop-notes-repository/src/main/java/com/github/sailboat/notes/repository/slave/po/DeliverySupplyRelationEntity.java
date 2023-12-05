package com.github.sailboat.notes.repository.slave.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.github.sailboat.notes.repository.domain.BaseDO;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 供货单号关系表
 * </p>
 *
 * @author spy
 * @since 2023-12-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("delivery_supply_relation")
public class DeliverySupplyRelationEntity extends BaseDO {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 订单明细行ID
     */
    private Long orderRowId;

    /**
     * 进场单需求明细单号
     */
    private Long deviceDemandId;

    /**
     * 设备spu_id
     */
    private String spuId;

    /**
     * 供货单号
     */
    private String supplyCode;

    /**
     * 仓库ID
     */
    private Long warehouseId;

    /**
     * 仓库名称
     */
    private String warehouseName;

    /**
     * 操作数量
     */
    private Integer num;

    /**
     * 单号类型 1-订单锁库、2-进场单请求流水号
     */
    private Integer type;

    /**
     * 隔离标识
     */
    private String tenantId;


}
