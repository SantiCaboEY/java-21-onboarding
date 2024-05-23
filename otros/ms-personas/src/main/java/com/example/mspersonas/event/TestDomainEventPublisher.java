package com.example.mspersonas.event;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestDomainEventPublisher extends SQSDomainEventPublisher {

    private int publishedEventsAmount;

    public TestDomainEventPublisher(){
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
