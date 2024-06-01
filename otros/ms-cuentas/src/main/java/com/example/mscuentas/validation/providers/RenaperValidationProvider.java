package com.example.mscuentas.validation.providers;

import com.example.mscuentas.client.renaper.RenaperClient;
import com.example.mscuentas.enums.AccountProduct;
import com.example.mscuentas.event.catalog.DomainEvent;
import com.example.mscuentas.event.catalog.PersonAddedEvent;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public final class RenaperValidationProvider implements ValidationProvider {

    private final RenaperClient renaperClient;

    public RenaperValidationProvider(final RenaperClient renaperClient) {
        this.renaperClient = renaperClient;
    }

    @Override
    public ValidationUnit getValidationUnit(DomainEvent event) throws ValidationProviderException {
        //Todo: Usar estructura dedicada en vez de mapa de Objects
        Map<String, Object> data = new HashMap<>();
        try {
            if (event instanceof PersonAddedEvent personAddedEvent) {
                var response = renaperClient.getAuthorizationStatus(Integer.decode(personAddedEvent.getDni()));
                data.put("isAuthorize", Objects.requireNonNull(response.getBody()).isAuthorize());
            } else throw new IllegalArgumentException("Cannot handle " + event.getEventName());
            return new RenaperValidationUnit(data);
        } catch (Exception e){
            throw new ValidationProviderException("Renaper provider", "Error while retrieving data from renaper.", e);
        }
    }

    public record RenaperValidationUnit(Map<String, Object> data) implements ValidationUnit {
        @Override
            public List<AccountProduct> filterProducts(List<AccountProduct> products) {
                if (Boolean.FALSE.equals(data.get("isAuthorized"))) {
                    var allowedProducts = List.of(AccountProduct.DEBIT_CARD);
                    products = products.stream().filter(allowedProducts::contains).toList();
                }
            return products;
        }
    }
}
