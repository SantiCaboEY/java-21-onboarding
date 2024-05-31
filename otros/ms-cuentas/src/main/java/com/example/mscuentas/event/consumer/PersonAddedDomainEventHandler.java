package com.example.mscuentas.event.consumer;

import com.example.mscuentas.dto.ApiException;
import com.example.mscuentas.event.catalog.DomainEvent;
import com.example.mscuentas.event.catalog.PersonAddedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PersonAddedDomainEventHandler implements DomainEventHandler{

    private static final Logger logger = LoggerFactory.getLogger(PersonAddedDomainEventHandler.class);

    private void handleEvent(PersonAddedEvent event) {
        logger.info("Handling event {}", event);
    }

    @Override
    public void handle(final DomainEvent event) {
        try {
            handleEvent((PersonAddedEvent) event);
        }catch (Exception e ){
            throw new ApiException(e, "Cannot handle person.add event due to misconfigured handler");
        }

    }
}
