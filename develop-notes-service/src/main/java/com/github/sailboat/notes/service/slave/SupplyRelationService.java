package com.github.sailboat.notes.service.slave;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.sailboat.notes.repository.slave.dao.DeliverySupplyRelationDao;

@Service
public class SupplyRelationService {

    @Resource
    DeliverySupplyRelationDao supplyRelationDao;

    public IPage findPage(Page page) {
        return supplyRelationDao.page(page);
    }

}
