package com.github.sailboat.notes.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 仓库
 * </p>
 *
 * @author 狂神说
 * @since 2023-08-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value="WmsWarehouse对象")
public class WmsWarehouse implements Serializable {

    private static final long serialVersionUID=1L;

    @TableField(value = "主键")
    private Long id;

    @TableField(value = "编码")
    private String code;

    @TableField(value = "名称")
    private String name;

    @TableField(value = "类型;1设备仓")
    private Integer type;

    @TableField(value = "0无 1大型仓 2中型仓 3小型仓 4微型仓")
    private Integer subType;

    @TableField(value = "状态;1正常")
    private Integer status;

    @TableField(value = "apaas地址")
    private String fullAddress;

    @TableField(value = "负责人id;账号id")
    private String managerId;

    @TableField(value = "负责人名称")
    private String managerName;

    @TableField(value = "启用标识;0未启用，1启用（默认)")
    private Boolean enableFlag;

    @TableField(value = "数据来源：1老系统迁移,2新系统(默认)")
    private Integer source;

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

    @TableField(value = "撤仓状态 0正常 1撤仓中")
    private Integer revokeStatus;


}
