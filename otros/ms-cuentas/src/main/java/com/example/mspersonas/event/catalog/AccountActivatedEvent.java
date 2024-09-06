package com.example.mspersonas.event.catalog;

import com.example.mscuentas.enums.AccountProduct;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

/**
 * Produced when an account is activated;
 */
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Getter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountActivatedEvent extends DomainEvent {

    private String personId;
	private String accountId;
    private List<AccountProduct> activatedProducts;
    private String personName;
    private String personLastName;
    private String personDni;

    public AccountActivatedEvent(){
        super("person.account.activated");
    }

    @Override
    protected String printNonSensitiveData() {
        return "personId = " + personId;
    }
}
