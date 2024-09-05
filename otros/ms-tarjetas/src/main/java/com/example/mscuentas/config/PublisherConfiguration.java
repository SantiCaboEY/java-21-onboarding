package com.example.mscuentas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
public class PublisherConfiguration {
    /**
     * An executor that runs Java 21 Virtual Threads. To be used by Event async processing.
     */
    @Bean
    public Executor virtualThreadExecutor(){
        return Executors.newVirtualThreadPerTaskExecutor();
    }

}
