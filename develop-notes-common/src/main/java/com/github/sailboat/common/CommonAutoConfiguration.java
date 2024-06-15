package com.github.sailboat.common;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

@Slf4j
@Configuration
@ConfigurationPropertiesScan
@ComponentScan
public class CommonAutoConfiguration {

    @Bean
    public ThreadPoolTaskExecutor createThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        int i = Runtime.getRuntime().availableProcessors();
        executor.setCorePoolSize(i * 2);
        executor.setMaxPoolSize(i * 2);
        executor.setQueueCapacity(i * 2 * 10);
        executor.setThreadNamePrefix("ThreadPoolTaskExecutor-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setKeepAliveSeconds(60);
        executor.initialize();

        log.info("ThreadPoolTaskExecutor init success");
        return executor;
    }

    @Bean
    public ThreadPoolExecutor createThreadPoolExecutor() {
        int i = Runtime.getRuntime().availableProcessors();
        int corePoolSize = i * 2;
        int maximumPoolSize = i * 2 * 10;
        long keepAliveTime = 60;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(maximumPoolSize);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        log.info("ThreadPoolExecutor init success");

        return executor;
    }

}
