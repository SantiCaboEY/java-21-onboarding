package com.example.mspersonas.event.catalog;

public class AccountRejectedEvent extends DomainEvent {
    @Override
    protected String printNonSensitiveData() {
        return "";
    }
}
