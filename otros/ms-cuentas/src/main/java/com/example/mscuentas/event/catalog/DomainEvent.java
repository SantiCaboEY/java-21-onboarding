package com.example.mscuentas.event.catalog;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public abstract class DomainEvent {
    //Metadata
    protected LocalDateTime timestamp;
    protected String eventName;
    //eventId
    //correlationId
    //spanId

    protected abstract String printNonSensitiveData();

    protected DomainEvent(){};

    protected DomainEvent(final String eventName){
        this.timestamp = LocalDateTime.now();
        this.eventName = eventName;
    }
    @Override
    public String toString(){
        return eventName + " DomainEvent(" + timestamp + ") : [" + printNonSensitiveData() + "]";
    }
}
