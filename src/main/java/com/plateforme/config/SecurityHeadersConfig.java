package com.plateforme.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.XXssProtectionHeaderWriter;

@Configuration
public class SecurityHeadersConfig {

    @Bean
    public SecurityFilterChain headersFilterChain(HttpSecurity http) throws Exception {
        http.headers(headers -> headers
            .contentSecurityPolicy(csp -> csp
                .policyDirectives("default-src 'self'; script-src 'self' 'unsafe-inline'"))
            .httpStrictTransportSecurity(hsts -> hsts
                .includeSubDomains(true)
                .preload(true)
                .maxAgeInSeconds(31536000))
            .xssProtection(xss -> xss.headerValue(XXssProtectionHeaderWriter.HeaderValue.ENABLED_MODE_BLOCK))
        );
        return http.build();
    }
}
