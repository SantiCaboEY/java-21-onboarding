package com.example.mspersonas.event.catalog;

import com.example.mspersonas.event.DomainEvent;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class PersonAddedEvent extends DomainEvent {
    private Integer id;
    private String name;
    private String lastName;
    private String dni;
    private int type;
    @Override
    protected String printNonSensitiveData() {
        return String.format("id: %s, name: %s, lastName:%s, dni: %s", id,name,lastName,dni);
    }
}
