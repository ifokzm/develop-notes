package com.github.sailboat.notes.communication;

import com.github.sailboat.notes.communication.netty.NettyServer;
import lombok.extern.slf4j.Slf4j;
import org.quartz.simpl.SimpleThreadPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.SimpleThreadPoolTaskExecutor;


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

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Value("${spring.communication.port}")
    private int port;

    @Bean
    public void startNetty() {
        taskExecutor.execute(() -> {
            NettyServer nettyServer = new NettyServer();
            nettyServer.bind(port);
        });
    }

}
