package com.github.sailboat.notes.repository.slave.dao.impl;

import com.github.sailboat.notes.repository.slave.po.DeliverySupplyRelationEntity;
import com.github.sailboat.notes.repository.slave.mapper.ext.DeliverySupplyRelationMapperExt;
import com.github.sailboat.notes.repository.slave.dao.DeliverySupplyRelationDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 供货单号关系表 服务实现类
 * </p>
 *
 * @author spy
 * @since 2023-12-04
 */
@Service
public class DeliverySupplyRelationDaoImpl extends ServiceImpl<DeliverySupplyRelationMapperExt, DeliverySupplyRelationEntity>implements DeliverySupplyRelationDao {

}
