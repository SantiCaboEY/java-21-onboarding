package com.example.mspersonas.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "personas.event")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class KafkaEventsProperties {
    private String producerTopic;
    private String consumerTopic;
}
