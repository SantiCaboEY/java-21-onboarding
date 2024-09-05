package com.example.mscuentas.event.consumer.handler;

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

    public AccountAddedEventHandler(final CardService cardService) {
        this.cardService = cardService;
    }

    private void handleEvent(AccountActivatedEvent event) {
        logger.info("Handling event {}", event);
        var dni = event.getPersonDni();
        if(Objects.isNull(dni)){
            logger.error("Invalid event received. It will be discarded: Dni is null. {}", event);
        } else {
            try{
            /**
             * LOGIC
             */
            } catch(Exception e){
                logger.error("Error DOING LOGIC");
                //This was a valid event, that failed because of api call to providers
                //Since it was a valid event, it shouldn't be lost. There needs to be a mechanism.
                //For now, we allow the base product, and rely on the client requesting the pending activations:
                /**
                 * MORE LOGIC
                 */
            }
            logger.info("Proceeding");
            try {
                /**
                 * MORE LOGIC
                 */
                logger.info(" Performed card activations: ");
            } catch (Exception e) {
                logger.error(
                        "Error activating cards for person[dni={}]. No cards will be activated: {}",
                        dni, e.getMessage(), e);}
        }
    }

    @Override
    public void handle(final AccountActivatedEvent event) {
        handleEvent(event);
    }
}
