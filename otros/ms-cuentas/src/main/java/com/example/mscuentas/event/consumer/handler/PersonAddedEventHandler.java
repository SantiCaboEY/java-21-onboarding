package com.example.mscuentas.event.consumer.handler;

import com.example.mscuentas.enums.AccountProduct;
import com.example.mscuentas.event.catalog.PersonAddedEvent;
import com.example.mscuentas.service.PersonBackgroundService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public final class PersonAddedEventHandler implements DomainEventHandler<PersonAddedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(PersonAddedEventHandler.class);
    private final PersonBackgroundService personBackgroundService;

    public PersonAddedEventHandler(final PersonBackgroundService personBackgroundService) {
        this.personBackgroundService = personBackgroundService;
    }

    private void handleEvent(PersonAddedEvent event) {
        logger.info("Handling event {}", event);
        var dni = event.getDni();
        if(Objects.isNull(dni)){
            logger.error("Invalid event received. It will be discarded: Dni is null. {}", event);
        } else {
            List<AccountProduct> availableProducts;
            try {
                availableProducts = ProductFilterTemplate.create()
                    .loadRule(personBackgroundService.getAntiterrorismStatus(dni) , theBoolean -> theBoolean,
                            AccountProduct.CREDIT_CARD, AccountProduct.GOLDEN_PACKAGE)
                    .loadRule(personBackgroundService.getBlacklistedStatus(dni), theBoolean -> theBoolean,
                            AccountProduct.CREDIT_CARD)
                    .loadRule(personBackgroundService.getScore(dni), score -> score < 10,
                            AccountProduct.GOLDEN_PACKAGE)
                    .getAvailableProducts();
            } catch(Exception e){
                logger.error(
                        "Error retrieving data from authorization providers. Only basic product will be activated: {}"
                        ,e.getMessage(), e);
                //This was a valid event, that failed because of api call to providers
                //Since it was a valid event, it shouldn't be lost. There needs to be a mechanism.
                //For now, we allow the base product, and rely on the client requesting the pending activations:
                availableProducts = List.of(AccountProduct.DEBIT_CARD);
            }
            logger.info("dni = {}. Obtained products to activate: {}", event.getDni(),availableProducts);
        }
    }

    @Override
    public void handle(final PersonAddedEvent event) {
        handleEvent(event);
    }
}
