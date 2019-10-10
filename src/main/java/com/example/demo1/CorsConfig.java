package com.example.demo1;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class CorsConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/conf/**")
                .allowedOrigins("localhost:8080/*")
                .allowCredentials(true)
                .allowedMethods("GET","POST")
                .maxAge(3600);
    }
}
