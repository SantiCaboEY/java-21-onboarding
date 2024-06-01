package com.example.mscuentas.validation.providers;

import com.example.mscuentas.event.catalog.DomainEvent;

/**
 * Manages retrieval of data from an external service.
 * Generates a {@link ValidationUnit} to participate in rule chains;
 */
public sealed interface ValidationProvider permits RenaperValidationProvider {
    ValidationUnit getValidationUnit(DomainEvent event) throws ValidationProviderException;
}
