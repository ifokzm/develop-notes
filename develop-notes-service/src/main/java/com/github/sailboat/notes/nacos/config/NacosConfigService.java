package com.github.sailboat.notes.nacos.config;

import org.springframework.stereotype.Service;

import com.alibaba.nacos.api.config.annotation.NacosValue;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@Service
public class NacosConfigService {

    @NacosValue(value = "${gushi:}", autoRefreshed = true)
    private String param;

}
