package com.github.sailboat.notes.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.sailboat.notes.entity.WmsStock;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 库存 Mapper 接口
 * </p>
 *
 * @author 狂神说
 * @since 2023-08-01
 */
@Mapper
public interface WmsStockMapper extends BaseMapper<WmsStock> {

}
