package com.example.mscuentas.event.consumer.handler;

import com.example.mscuentas.event.producer.DomainEventPublisher;
import com.example.mscuentas.service.CardService;
import com.example.mspersonas.event.catalog.AccountActivatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public final class AccountAddedEventHandler implements DomainEventHandler<AccountActivatedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(AccountAddedEventHandler.class);
    private final CardService cardService;
    private final DomainEventPublisher publisher;

    public AccountAddedEventHandler(final CardService cardService,
                                    final DomainEventPublisher publisher) {
        this.cardService = cardService;
        this.publisher = publisher;
    }

    private void handleEvent(AccountActivatedEvent event) {
        logger.info("Handling event {}", event);
        var accountId = event.getAccountId();
        if(Objects.isNull(accountId)){
            logger.error("Invalid event received. It will be discarded: accountId is null. {}", event);
        } else {
            try{
                cardService.addProducts(event.getActivatedProducts(), Integer.decode(event.getAccountId()));
            } catch(Exception e){
                logger.error("Error activating cards for account: {}", event.getAccountId());
                //This was a valid event, that failed because of api call to providers
                //Since it was a valid event, it shouldn't be lost. There needs to be a mechanism.
                //For now, we allow the base product, and rely on the client requesting the pending activations:
            }
            logger.info("Proceeding");
            try {
                logger.info(" Performed card activations: ");
            } catch (Exception e) {
                logger.error(
                        "Error activating cards for person[accountId={}]. No cards will be activated: {}",
                        accountId, e.getMessage(), e);}
        }
    }

    @Override
    public void handle(final AccountActivatedEvent event) {
        handleEvent(event);
    }
}
