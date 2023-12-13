package com.github.sailboat.notes.repository.slave.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.github.sailboat.notes.handler.GeoLineTypeHandler;
import com.github.sailboat.notes.repository.domain.BaseDO;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.locationtech.jts.geom.LineString;

/**
 * <p>
 * 
 * </p>
 *
 * @author spy
 * @since 2023-12-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName(value = "test", autoResultMap = true)
public class TestEntity extends BaseDO {

    private static final long serialVersionUID=1L;

    private Long id;
    @TableField(typeHandler = GeoLineTypeHandler.class)
    private LineString line;


}
