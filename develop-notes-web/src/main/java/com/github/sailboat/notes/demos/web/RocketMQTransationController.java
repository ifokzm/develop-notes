package com.github.sailboat.notes.demos.web;

import com.github.sailboat.notes.message.RocketMQManager;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RocketMQTransationController {

    @Autowired
    private RocketMQManager rocketMQManager;

    /**
     * 发送事务消息
     *
     * @param msg
     */
    @RequestMapping("/sendTransationMessage")
    @ResponseBody
    public String sendTransationMessage(@RequestParam("msg") String msg) {
        //发送事务消息:采用的是sendMessageInTransaction方法，返回结果为TransactionSendResult对象，该对象中包含了事务发送的状态、本地事务执行的状态等
        //参数一：topic   如果想添加tag,可以使用"topic:tag"的写法
        //参数二：消息内容
        //参数三：
        TransactionSendResult result = rocketMQManager.getInstance().sendMessageInTransaction("test-topic-transation", MessageBuilder.withPayload(msg).build(), null);
        //发送状态
        String sendStatus = result.getSendStatus().name();
        //本地事务执行状态
        String localState = result.getLocalTransactionState().name();
        System.out.println("发送状态:" + sendStatus + ";本地事务执行状态" + localState);

        return "发送状态:" + sendStatus + ";本地事务执行状态" + localState;
    }

}
