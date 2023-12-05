package com.github.sailboat.notes.repository.master.dao.impl;

import com.github.sailboat.notes.repository.master.po.DeviceInfoEntity;
import com.github.sailboat.notes.repository.master.mapper.ext.DeviceInfoMapperExt;
import com.github.sailboat.notes.repository.master.dao.DeviceInfoDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 设备信息 服务实现类
 * </p>
 *
 * @author spy
 * @since 2023-12-04
 */
@Service
public class DeviceInfoDaoImpl extends ServiceImpl<DeviceInfoMapperExt, DeviceInfoEntity>implements DeviceInfoDao {

}
