package com.github.sailboat.notes.communication;

import com.github.sailboat.notes.communication.netty.NettyServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhumeng
 * @version 1.0
 * @description: TODO
 * @date 2024/6/12
 */
@Slf4j
@Configuration
@ConfigurationPropertiesScan
@ComponentScan
@ConditionalOnExpression(value = "${communication.switch:false}")
public class CommunicationAutoConfiguration {

    @Bean
//    @ConditionalOnExpression("#{'true'.equals(environment['netty.start.switch'])}")
    public void startNetty() {
        new Thread(() -> {
            NettyServer nettyServer = new NettyServer();
            nettyServer.bind(8888);
        }).start();
    }

}
