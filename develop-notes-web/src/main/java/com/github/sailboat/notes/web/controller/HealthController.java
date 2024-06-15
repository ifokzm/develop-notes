package com.github.sailboat.notes.web.controller;

import com.github.sailboat.common.annotation.NotControllerResponseAdvice;
import com.influxdb.client.InfluxDBClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {


    @Autowired
    InfluxDBClient influxDBClient;

    @GetMapping("/influx/version")
    public String version() {
        return influxDBClient.version();
    }
    @GetMapping("/health")
    @NotControllerResponseAdvice
    public String health() {
        return "success";
    }
}