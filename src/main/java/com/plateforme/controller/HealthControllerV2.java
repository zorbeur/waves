package com.plateforme.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/health2")
public class HealthControllerV2 {

    @GetMapping
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("OK");
    }
}
