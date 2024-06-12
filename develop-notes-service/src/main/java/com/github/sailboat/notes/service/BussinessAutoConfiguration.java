package com.github.sailboat.notes.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
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
@ConditionalOnExpression(value = "${bussiness.switch:true}")
public class BussinessAutoConfiguration {
}
