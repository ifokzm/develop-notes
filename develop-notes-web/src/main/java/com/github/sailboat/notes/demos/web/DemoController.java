package com.github.sailboat.notes.demos.web;

import javax.annotation.Resource;

import com.github.sailboat.notes.repository.slave.po.TestEntity;
import com.github.sailboat.notes.service.slave.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.sailboat.notes.service.master.DeviceInfoService;
import com.github.sailboat.notes.service.slave.SupplyRelationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("demo")
public class DemoController {

    @Resource
    DeviceInfoService deviceInfoService;

    @Resource
    SupplyRelationService supplyRelationService;

    @Resource
    TestService testService;

    @GetMapping("/getDeviceInfo")
    public IPage getDeviceInfo(Page page) {
        // 将vo => po，进行page查询
        return deviceInfoService.findPage(page);
    }

    @GetMapping("/getSupplyRelation")
    public IPage getSupplyRelation(Page page) {
        // 将vo => po，进行page查询
        return supplyRelationService.findPage(page);
    }

    @GetMapping("/getTestLineString")
    public TestEntity getTestLineString() {
        // 将vo => po，进行page查询
        return testService.find();
    }

    @GetMapping("/setTestLineString")
    public String setTestLineString() {
        // 将vo => po，进行page查询
         testService.save();
         return "OK";
    }

}
