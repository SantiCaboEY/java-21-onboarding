package com.example.mscuentas.event.catalog;

public class AccountRejectedEvent extends DomainEvent{
    @Override
    protected String printNonSensitiveData() {
        return "";
    }
}
