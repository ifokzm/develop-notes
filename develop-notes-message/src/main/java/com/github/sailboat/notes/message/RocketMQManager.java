//package com.github.sailboat.notes.message;
//
//
//import org.apache.rocketmq.spring.core.RocketMQTemplate;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//
//@Component
//public class RocketMQManager {
//
//    @Resource
//    RocketMQTemplate mqTemplate;
//
//    public void syncSendOrderly(String s, String s1, String s2) {
//        mqTemplate.syncSendOrderly(s, s1, s2);
//    }
//
//    public final RocketMQTemplate getInstance() {
//        return mqTemplate;
//    }
//}
