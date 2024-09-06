package com.example.mspersonas.event.catalog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Getter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CardActivatedEvent extends DomainEvent {
    private String number;
    private Integer accountNumber;
    private String expiryDate;
    private Integer pin;
    private String status;
    private String issueDate;
    private String type;


    public CardActivatedEvent(){
        super("card.create");
    }

    @Override
    protected String printNonSensitiveData() {
        return "cardNumber = " + number.substring(0,4) + "********" + number.substring(12,16);
    }
}
