package com.example.mspersonas.config;

import com.example.mspersonas.event.DomainEventPublisher;
import com.example.mspersonas.event.SQSDomainEventPublisher;
import com.example.mspersonas.event.TestDomainEventPublisher;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Bean
    @Profile("!test")
    public DomainEventPublisher sqsDomainEventPublisher() {
        return new SQSDomainEventPublisher();
    }

    @Bean
    @Profile("test")
    public DomainEventPublisher testDomainEventPublisher() {
        return new TestDomainEventPublisher();
    }
}
