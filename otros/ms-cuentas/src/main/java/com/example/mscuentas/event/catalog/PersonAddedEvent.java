package com.example.mscuentas.event.catalog;

import lombok.*;

/**
 * Produced when a person is added, regardless of previous states.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
public class PersonAddedEvent extends DomainEvent {
    private Integer id;
    private String name;
    private String lastName;
    private String dni;
    private int type;

    public PersonAddedEvent(){
        super();
        this.setEventName("person.add");
    }

    @Override
    protected String printNonSensitiveData() {
        return String.format("id: %s, name: %s, lastName:%s, dni: %s", id,name,lastName,dni);
    }
}
