package com.example.mscuentas.event.consumer.handler;

import com.example.mscuentas.enums.AccountProduct;
import com.example.mscuentas.service.AccountService;
import com.example.mscuentas.service.PersonBackgroundService;
import com.example.mspersonas.event.catalog.PersonAddedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Component
public final class PersonAddedEventHandler implements DomainEventHandler<PersonAddedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(PersonAddedEventHandler.class);
    private final PersonBackgroundService personBackgroundService;
    private final AccountService accountService;

    public PersonAddedEventHandler(final PersonBackgroundService personBackgroundService,
                                   final AccountService accountService) {
        this.personBackgroundService = personBackgroundService;
        this.accountService = accountService;
    }

    private void handleEvent(PersonAddedEvent event) {
        logger.info("Handling event {}", event);
        var dni = event.getDni();
        if(Objects.isNull(dni)){
            logger.error("Invalid event received. It will be discarded: Dni is null. {}", event);
        } else {
            List<AccountProduct> availableProducts;
            try {
                var worldSysFutureResponse = personBackgroundService.getAntiterrorismStatus(dni);
                var renaperFutureResponse = personBackgroundService.getBlacklistedStatus(dni);
                var verazFutureResponse = personBackgroundService.getScore(dni);
                logger.info("debug, {}", worldSysFutureResponse.get());
                logger.info("debug, {}", renaperFutureResponse.get());
                logger.info("debug, {}", verazFutureResponse.get());
                availableProducts = ProductFilterTemplate.create()
                    .loadRule(worldSysFutureResponse, isATerrorist -> isATerrorist,
                            AccountProduct.values())
                    .loadRule(renaperFutureResponse, isOk -> !isOk,
                            AccountProduct.CREDIT_CARD_GOLD, AccountProduct.CREDIT_CARD_PLATINUM, AccountProduct.CREDIT_CARD_BASIC)
                    .loadRule(verazFutureResponse, score -> score < 10,
                            AccountProduct.CREDIT_CARD_GOLD, AccountProduct.CREDIT_CARD_PLATINUM)
                    .getAvailableProducts();
            } catch(Exception e){
                logger.error(
                        "Error retrieving data from authorization providers for person[dni={}]. Only basic product will be activated: {}",
                        dni, e.getMessage(), e);
                //This was a valid event, that failed because of api call to providers
                //Since it was a valid event, it shouldn't be lost. There needs to be a mechanism.
                //For now, we allow the base product, and rely on the client requesting the pending activations:
                availableProducts = List.of(AccountProduct.BASIC_ACCOUNT);
            }
            logger.info("dni = {}. Proceeding to activate the following products: {}", event.getDni(), availableProducts);
            try {
                accountService.addAccount(event, availableProducts);
                logger.info("dni = {}. Performed product activations: {}", event.getDni(),availableProducts);
            } catch (Exception e) {
                logger.error(
                        "Error activating products for person[dni={}]. No products will be activated: {}",
                        dni, e.getMessage(), e);
                //Same consideration here.
            }

        }
    }

    @Override
    public void handle(final PersonAddedEvent event) {
        handleEvent(event);
    }
}
