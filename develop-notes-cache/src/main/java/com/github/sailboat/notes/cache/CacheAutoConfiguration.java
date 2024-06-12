package com.github.sailboat.notes.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
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
public class CacheAutoConfiguration {
}
