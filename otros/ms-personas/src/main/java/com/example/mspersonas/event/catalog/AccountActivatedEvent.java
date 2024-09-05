package com.example.mspersonas.event.catalog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Produced when an account is activated;
 */
@EqualsAndHashCode(callSuper = true)
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountActivatedEvent extends DomainEvent{

    private String personId;

    public AccountActivatedEvent(){
        super();
        this.setEventName("person.account.activated");
    }

    @Override
    protected String printNonSensitiveData() {
        return "";
    }
}
