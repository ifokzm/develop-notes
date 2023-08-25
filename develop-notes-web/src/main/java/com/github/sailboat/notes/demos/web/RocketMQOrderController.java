package com.github.sailboat.notes.demos.web;

import com.github.sailboat.notes.message.RocketMQManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Slf4j
@Controller
public class RocketMQOrderController {
    @Resource
    private RocketMQManager mqManager;
    /**
     * 发送同步顺序消息
     */
    @RequestMapping(value = "/testSyncOrderSend", method = RequestMethod.GET)
    @ResponseBody
    public String testSyncSend(){
        log.info("enter testSyncSend....");
        //参数一：topic   如果想添加tag,可以使用"topic:tag"的写法
        //参数二：消息内容
        //参数三：hashKey 用来计算决定消息发送到哪个消息队列， 一般是订单ID，产品ID等
        mqManager.syncSendOrderly("test-topic-orderly","111111创建","111111");
        mqManager.syncSendOrderly("test-topic-orderly","111111支付","111111");
        mqManager.syncSendOrderly("test-topic-orderly","111111完成","111111");

        mqManager.syncSendOrderly("test-topic-orderly","222222创建","222222");
        mqManager.syncSendOrderly("test-topic-orderly","222222支付","222222");
        mqManager.syncSendOrderly("test-topic-orderly","222222完成","222222");
        log.info("exit testSyncSend....");
        return "成功";
    }

}
