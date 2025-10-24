package com.plateforme.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/secure")
public class HealthSecurityController {

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> me() {
        return ResponseEntity.ok("ok");
    }
}
