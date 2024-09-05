package com.example.mspersonas.event.catalog;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
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
