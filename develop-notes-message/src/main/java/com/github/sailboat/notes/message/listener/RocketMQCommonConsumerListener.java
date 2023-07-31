package com.github.sailboat.notes.message.listener;

import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(consumerGroup = "test", topic = "test-topic-orderly", consumeMode = ConsumeMode.ORDERLY)
public class RocketMQCommonConsumerListener implements RocketMQListener<String> {
    @Override
    public void onMessage(String s) {
        System.out.println("consumer 顺序消费，收到消息:" + s);
    }
}
