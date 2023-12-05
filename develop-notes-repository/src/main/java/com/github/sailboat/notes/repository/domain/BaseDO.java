package com.github.sailboat.notes.repository.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseDO implements Serializable {
    /**
     * 创建时间
     */
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private Date createdAt;

    /**
     * 创建人
     */
    @TableField(value = "created_by", fill = FieldFill.INSERT)
    private Long createdBy;

    /**
     * 更新时间
     */
    @TableField(value = "updated_at", fill = FieldFill.UPDATE)
    private Date updatedAt;

    /**
     * 更新人
     */
    @TableField(value = "updated_by", fill = FieldFill.UPDATE)
    private Long updatedBy;
}
