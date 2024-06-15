package com.github.sailboat.notes.repository.timing;


import com.github.sailboat.notes.repository.timing.conf.InfluxDBConfig;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.InfluxDBClientOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class InfluxDBManager {


    @Autowired
    InfluxDBConfig config;


    @Bean
    public InfluxDBClient initInfluxDBClient() {
        InfluxDBClientOptions options = InfluxDBClientOptions.builder().url(config.getUrl()).authenticateToken(config.getToken().toCharArray()).org(config.getOrg()).bucket(config.getBucket()).build();
        return InfluxDBClientFactory.create(options);
    }



}
