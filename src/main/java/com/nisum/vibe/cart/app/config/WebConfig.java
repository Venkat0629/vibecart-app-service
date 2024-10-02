package com.nisum.vibe.cart.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Apply CORS settings to all endpoints
                .allowedOrigins("*") // Allow any origin
                .allowedMethods("*") // Allow any method (GET, POST, etc.)
                .allowedHeaders("*") // Allow any header
                .allowCredentials(false); // Do not allow credentials (like cookies)
    }
}
