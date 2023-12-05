package com.github.sailboat.notes.repository.master.po;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.github.sailboat.notes.repository.domain.BaseDO;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 设备信息
 * </p>
 *
 * @author spy
 * @since 2023-12-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("device_info")
public class DeviceInfoEntity extends BaseDO {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 设备编号
     */
    private String code;

    /**
     * 设备出厂编号
     */
    private String identity;

    /**
     * 设备自编号
     */
    private String selfIdentity;

    /**
     * 三级类目编号
     */
    private String categoryCode;

    /**
     * 一级分类
     */
    private String firstCategory;

    /**
     * 二级分类
     */
    private String secondCategory;

    /**
     * 三级分类
     */
    private String thirdCategory;

    /**
     * 型号ID
     */
    private String modelId;

    /**
     * 型号
     */
    private String model;

    /**
     * 品牌ID
     */
    private String brandId;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 高度
     */
    private String height;

    /**
     * 动力类型
     */
    private String powerType;

    /**
     * 来源:1常规采购 2试用机 3转租 4代运营
     */
    private Integer source;

    /**
     * 自营设备
     */
    private Boolean selfDevice;

    /**
     * 导入人
     */
    private String importUser;

    /**
     * 导入时间
     */
    private Date importDate;

    /**
     * 供应商发货时间
     */
    private Date deliveryTime;

    /**
     * 质保时长(年)
     */
    private Integer warrantyPeriod;

    /**
     * 入库状态;1未入库 2已入库
     */
    private Integer warehouseStatus;

    /**
     * 首次入库时间
     */
    private Date warehouseDate;

    /**
     * 首次入库所在仓库
     */
    private String warehouseName;

    /**
     * 首次入库所在大区
     */
    private String warehouseArea;

    /**
     * GPS编号
     */
    private String gpsCode;

    /**
     * GPS终端类型
     */
    private String gpsType;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 租户号
     */
    private Long tenantId;

    /**
     * 类目图片
     */
    private String categoryIcon;

    /**
     * 发动机型号
     */
    private String engineModel;

    /**
     * 采购价格
     */
    private BigDecimal purchasePrice;

    /**
     * 入库需求单ID
     */
    private Long purchaseDeliveryDemandId;


}
