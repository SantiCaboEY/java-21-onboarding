package com.example.mspersonas.config;

import com.example.mspersonas.event.producer.DomainEventPublisher;
import com.example.mspersonas.event.producer.KafkaDomainEventPublisher;
import com.example.mspersonas.event.producer.SQSDomainEventPublisher;
import com.example.mspersonas.event.producer.MockedDomainEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

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
