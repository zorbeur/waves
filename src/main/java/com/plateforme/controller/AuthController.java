package com.plateforme.controller;

import com.plateforme.dto.auth.JwtResponse;
import com.plateforme.dto.auth.LoginRequest;
import com.plateforme.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtService.generateToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtResponse> refreshToken() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        String token = jwtService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok().build();
    }
}
