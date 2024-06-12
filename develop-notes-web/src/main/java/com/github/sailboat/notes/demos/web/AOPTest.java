package com.github.sailboat.notes.demos.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.sailboat.notes.annotation.RepeatSubmit;

@RestController
@RequestMapping("/test")
public class AOPTest {

    // @Resource
    // private IUserService userService;

    // 默认1s，方便测试查看，写10s
//    @RepeatSubmit(expireTime = 10)
//    @PostMapping("/SysLog")
//    public String saveSysLog(@RequestBody Object userParam) {
//        return "";
//    }
}