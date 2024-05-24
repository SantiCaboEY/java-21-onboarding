package com.example.mspersonas.event.producer;

import com.example.mspersonas.event.catalog.DomainEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MockedDomainEventPublisher extends SQSDomainEventPublisher {

    private int publishedEventsAmount;

    public MockedDomainEventPublisher(){
        super();
        publishedEventsAmount = 0;
    }

    @Override
    protected void handlePublish(DomainEvent domainEvent) {
        publishedEventsAmount++;
        super.handlePublish(domainEvent);
    }

    public int getPublishedEventsAmount(){
        var result = publishedEventsAmount;
        publishedEventsAmount = 0;
        return result;
    }
}
