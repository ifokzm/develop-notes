package com.github.sailboat.notes.repository.timing.conf;


import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.InfluxDBClientOptions;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhumeng
 * @version 1.0
 * @description: TODO
 * @date 2024/6/13
 */

@Configuration
@Data
public class InfluxDBConfig {

    @Value("${influxdb.url}")
    private String url;

    @Value("${influxdb.token}")
    private String token;

    @Value("${influxdb.bucket}")
    private String bucket;

    @Value("${influxdb.org}")
    private String org;


}
