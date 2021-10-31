package com.leantech.pruebaleantech.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;

@Configuration
public class ApplicationConfig {

	@Bean
    public Gson gsonConfig() {
        return new Gson();
    }
}
