package com.example.mscuentas.validation;

import com.example.mscuentas.event.catalog.DomainEvent;
import com.example.mscuentas.validation.providers.ValidationProvider;
import com.example.mscuentas.validation.providers.ValidationProviderException;
import com.example.mscuentas.validation.providers.ValidationUnit;
import com.example.mscuentas.validation.providers.RenaperValidationProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Executes rules by obtaining a {@link ValidationSubject} from a list of {@link ValidationProvider}.
 * <p>
 * Requests to instances {@link ValidationProvider} are done in parallel using Virtual Threads.
 */
@Component
@Slf4j
public class ValidationEngine {

    private final List<ValidationProvider> validationProviders;

    public ValidationEngine(RenaperValidationProvider renaperValidationProvider){
        this.validationProviders = List.of(renaperValidationProvider);
    }

    /**
     *  Generates a bundle of retrieved data ready to run validations.
     *  The retrieved data and the methods to run each validation rule ara stored un instances of {@link ValidationUnit}.
     *  There's one instance for each {@link com.example.mscuentas.validation.providers.ValidationProvider} called.
     *  Each validation is run by chaining the filters provided by each {@link ValidationUnit}.
     *
     * @param event containing the data to feed providers
     * @return a {@link ValidationSubject} instance.
     */
    public ValidationSubject retrieveDataForValidations(DomainEvent event) {

        var validationUnits = Collections.<ValidationUnit>synchronizedList(new ArrayList<>());
        try(var executor = Executors.newVirtualThreadPerTaskExecutor()){
            var futures = validationProviders.stream()
                    .map(p -> createFuture(p,event, validationUnits, executor))
                    .toArray();
            CompletableFuture.allOf((CompletableFuture<?>[])futures);

        }catch(ValidationProviderException e){
            log.error("Error retrieving data from {} : {}",e.getProviderName(), e.getMessage(), e);
            throw e;
        }
        return new ValidationSubject(validationUnits);
    }

    private CompletableFuture<Boolean> createFuture(ValidationProvider provider,
                                                              DomainEvent event,
                                                              List<ValidationUnit> validationUnits,
                                                              Executor executor){
        return CompletableFuture.supplyAsync(() -> validationUnits.add(provider.getValidationUnit(event)), executor);

    }
}
