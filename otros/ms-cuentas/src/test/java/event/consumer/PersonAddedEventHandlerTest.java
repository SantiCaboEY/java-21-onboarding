package event.consumer;

import com.example.mscuentas.enums.AccountProduct;
import com.example.mscuentas.event.catalog.PersonAddedEvent;
import com.example.mscuentas.event.consumer.handler.PersonAddedEventHandler;
import com.example.mscuentas.validation.ValidationEngine;
import com.example.mscuentas.validation.ValidationSubject;
import com.example.mscuentas.validation.providers.ValidationProviderException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class PersonAddedEventHandlerTest {

    private PersonAddedEventHandler handler;

    private ValidationEngine validationEngine;
    private ValidationSubject validationSubject;

    @BeforeEach
    void initialize(){
        this.validationEngine = Mockito.mock(ValidationEngine.class);
        this.validationSubject = Mockito.mock(ValidationSubject.class);
        handler = new PersonAddedEventHandler(validationEngine);
    }
    @Test
    void givenValidEventThenSucced(){
        Mockito.when(validationEngine.retrieveDataForValidations(Mockito.any()))
                .thenReturn(this.validationSubject);
        Mockito.when(validationSubject.getAllowedProducts()).thenReturn(List.of(AccountProduct.DEBIT_CARD));

        Assertions.assertDoesNotThrow(() -> handler.handle(personAddEvent()));
    }

    @Test
    void givenProviderIsDownThenFail(){
        Mockito.when(validationEngine.retrieveDataForValidations(Mockito.any()))
                .thenThrow(new ValidationProviderException("Renaper", "Failed", null));

        var event = personAddEvent();
        Assertions.assertThrows(ValidationProviderException.class, () -> handler.handle(event));
    }


    private static PersonAddedEvent personAddEvent() {
        return PersonAddedEvent.builder()
        .personId(1)
        .dni("22333444")
        .name("John")
        .lastName("Doe")
        .type(1).build();
    }
}
