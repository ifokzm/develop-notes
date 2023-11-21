package com.github.sailboat.notes;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


//@EnableDiscoveryClient
//@EnableApolloConfig
@SpringBootApplication
@MapperScan(value = "com.github.sailboat.notes.mapper")
@EnableTransactionManagement
@Slf4j
public class Application {

    public static void main(String[] args) {
        /*
         * 指定使用的日志框架，否则将会报错
         * RocketMQLog:WARN No appenders could be found for logger (io.netty.util.internal.InternalThreadLocalMap).
         * RocketMQLog:WARN Please initialize the logger system properly.
         */
        System.setProperty("rocketmq.client.logUseSlf4j", "true");
        SpringApplication.run(Application.class, args);
    }

}
