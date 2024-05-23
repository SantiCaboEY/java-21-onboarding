package service;

import com.example.mspersonas.dto.ApiException;
import com.example.mspersonas.dto.CreatePersonDto;
import com.example.mspersonas.enums.PersonStatus;
import com.example.mspersonas.event.DomainEventPublisher;
import com.example.mspersonas.event.catalog.PersonAddedEvent;
import com.example.mspersonas.model.Person;
import com.example.mspersonas.repository.PersonRepository;
import com.example.mspersonas.service.PersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.Mockito;

import java.util.Optional;

class PersonServiceTest {

    private PersonService personService;
    private PersonRepository personRepository;
    private DomainEventPublisher domainEventPublisher;
    private CreatePersonDto personDto;

    @BeforeEach
    void initialize(){
        personRepository = Mockito.mock(PersonRepository.class);
        domainEventPublisher = Mockito.mock(DomainEventPublisher.class);
        personService = new PersonService(personRepository, domainEventPublisher);
        personDto = new CreatePersonDto(1, "John","Doe", "11222333");
    }
    @Test
    void givenNonExistingPersonSucceed(){
        Mockito.when(personRepository.findByDni(Mockito.anyString()))
                .thenReturn(Optional.ofNullable(person(PersonStatus.CANCELADO)));
        Mockito.when(personRepository.save(Mockito.any(Person.class))).thenReturn(person());

        var result = personService.addPerson(personDto);

        Assertions.assertEquals("1", result.id());

        Mockito.verify(domainEventPublisher, Mockito.times(1))
                .publish(Mockito.any(PersonAddedEvent.class));

    }

    @Test
    void givenExistingPersonSucceed(){
        Mockito.when(personRepository.findByDni(Mockito.anyString())).thenReturn(Optional.empty());
        Mockito.when(personRepository.save(Mockito.any(Person.class))).thenReturn(person());

        var result = personService.addPerson(personDto);

        Assertions.assertEquals("1", result.id());
        Mockito.verify(domainEventPublisher, Mockito.times(1))
                .publish(Mockito.any(PersonAddedEvent.class));
    }

    @ParameterizedTest()
    @EnumSource(value = PersonStatus.class, names = {"ACTIVO", "INACTIVO", "SUSPENDIDO", "BLOQUEADO"})
    void failInvalidState(PersonStatus invalidStatus){
        Mockito.when(personRepository.findByDni(Mockito.anyString()))
                .thenReturn(Optional.ofNullable(person(invalidStatus)));

        Assertions.assertThrows(ApiException.class, () -> personService.addPerson(personDto));
        Mockito.verify(domainEventPublisher, Mockito.never()).publish(Mockito.any(PersonAddedEvent.class));
    }

    private static Person person(){
        return person(PersonStatus.INACTIVO);
    }

    private static Person person(PersonStatus personStatus){
        return Person.builder()
                .id(1)
                .type(1)
                .dni("11222333")
                .name("John")
                .lastName("Doe")
                .status(personStatus)
                .build();
    }
}
