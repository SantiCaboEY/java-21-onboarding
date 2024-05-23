package com.example.mspersonas.event;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class DomainEvent {
    private LocalDateTime timestamp;

    protected abstract String printNonSensitiveData();

    protected DomainEvent(){
        this.timestamp = LocalDateTime.now();
    }
    @Override
    public String toString(){
        return "DomainEvent(" + timestamp + ") : [" + printNonSensitiveData() + "]";
    }

    //Todo: Recibir y procesar AccountActivatedEvent
}
