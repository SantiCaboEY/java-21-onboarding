package com.example.mscuentas.event.consumer.handler;

import com.example.mscuentas.event.catalog.PersonAddedEvent;
import com.example.mscuentas.validation.ValidationEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public final class PersonAddedEventHandler implements DomainEventHandler<PersonAddedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(PersonAddedEventHandler.class);

    private final ValidationEngine validationEngine;

    public PersonAddedEventHandler(final ValidationEngine validationEngine) {
        this.validationEngine = validationEngine;
    }

    private void handleEvent(PersonAddedEvent event) {
        logger.info("Handling event {}", event);
        var productsToActivate = validationEngine.retrieveDataForValidations(event).getAllowedProducts();
        logger.info("For person identification[dni = {}]. Obtained products to activate: {}",
                event.getDni(),productsToActivate);
        //Todo: producir AccountCreatedEvent
    }

    @Override
    public void handle(final PersonAddedEvent event) {
        handleEvent(event);
    }
}
