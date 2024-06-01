package com.example.mscuentas.event.catalog;

import lombok.*;

/**
 * Produced when a person is added, regardless of previous states.
 */
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Builder
@Getter
public class PersonAddedEvent extends DomainEvent {
    private Integer personId;
    private String name;
    private String lastName;
    private String dni;
    private int type;

    public PersonAddedEvent(){
        super("person.add");
    }

    @Override
    protected String printNonSensitiveData() {
        return String.format("id: %s, name: %s, lastName:%s, dni: %s", personId, name,lastName,dni);
    }
}
