package com.github.sailboat.notes;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
import com.alibaba.nacos.spring.context.annotation.discovery.EnableNacosDiscovery;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;


//@EnableDiscoveryClient
//@EnableApolloConfig
@SpringBootApplication
@MapperScan(value = "com.github.sailboat.notes.mapper")
@EnableTransactionManagement
@Slf4j
@EnableNacosDiscovery
@EnableNacosConfig
public class Application {

    @Value("${spring.application.name}")
    private String serviceName;

    @Value("${xxl.job.admin.addresses:}")
    private String adminAddresses;

    @Value("${xxl.job.accessToken:}")
    private String accessToken;

    @Value("${xxl.job.executor.ip:}")
    private String ip;

    @Value("${xxl.job.executor.port:9999}")
    private Integer port;

    @Value("${xxl.job.executor.logpath:}")
    private String logPath;

    @Value("${xxl.job.executor.logretentiondays:30}")
    private Integer logRetentionDays;

    public static void main(String[] args) {
        /*
         * 指定使用的日志框架，否则将会报错
         * RocketMQLog:WARN No appenders could be found for logger (io.netty.util.internal.InternalThreadLocalMap).
         * RocketMQLog:WARN Please initialize the logger system properly.
         */
        System.setProperty("rocketmq.client.logUseSlf4j", "true");
        SpringApplication.run(Application.class, args);
    }

    @NacosInjected
    private NamingService namingService;

    @PostConstruct
    public void registerInstance() throws NacosException {
        namingService.registerInstance(serviceName, "192.168.35.21", 8080);
    }

    @Bean
    public XxlJobSpringExecutor xxlJobExecutor() {
        log.info(">>>>>>>>>>> xxl-job config init.");
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(adminAddresses);
        xxlJobSpringExecutor.setAppname(serviceName);
        xxlJobSpringExecutor.setIp(ip);
        xxlJobSpringExecutor.setPort(port);
        xxlJobSpringExecutor.setAccessToken(accessToken);
        xxlJobSpringExecutor.setLogPath(logPath);
        xxlJobSpringExecutor.setLogRetentionDays(logRetentionDays);
        log.info(">>>>>>>>>>> xxl-job config finished.");
        return xxlJobSpringExecutor;
    }

}
