package com.github.sailboat.notes.web.controller;

import com.github.sailboat.common.annotation.NotControllerResponseAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/health")
    @NotControllerResponseAdvice
    public String health() {
        return "success";
    }
}