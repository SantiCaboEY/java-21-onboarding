package com.example.mspersonas.event.catalog;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class DomainEvent {
    private LocalDateTime timestamp;
    private String eventName;

    protected abstract String printNonSensitiveData();

    protected DomainEvent(){
        this.timestamp = LocalDateTime.now();
    }
    @Override
    public String toString(){
        return eventName + " DomainEvent(" + timestamp + ") : [" + printNonSensitiveData() + "]";
    }
}
