package com.github.sailboat.notes.service.master;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.sailboat.notes.repository.master.dao.DeviceInfoDao;

@Service
public class DeviceInfoService {

    @Resource
    DeviceInfoDao deviceInfoDao;

    public IPage findPage(Page page) {
        return deviceInfoDao.pageMaps(page);
    }
}
