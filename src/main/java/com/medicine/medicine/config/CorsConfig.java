package com.medicine.medicine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOriginPatterns("*")
                        .allowedMethods("GET", "POST", "PATCH", "PUT", "DELETE")
                        .allowedHeaders("Authorization", "Content-Type")
                        .exposedHeaders("Custom-Header")
                        .allowCredentials(true)
                        .maxAge(3600);
            }
        };
    }
}