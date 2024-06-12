package com.github.sailboat.notes.communication;

import com.github.sailboat.notes.communication.netty.NettyServer;
import lombok.extern.slf4j.Slf4j;
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
public class CommunicationAutoConfiguration {

    @Bean
    public void startNetty() {
        new Thread(() -> {
            NettyServer nettyServer = new NettyServer();
            nettyServer.bind(8888);
            log.info("启动Netty成功，port:8888");
        }).start();
    }

}
