package event.consumer;

import com.example.mscuentas.event.catalog.PersonAddedEvent;
import com.example.mscuentas.event.consumer.handler.PersonAddedEventHandler;
import com.example.mscuentas.service.PersonBackgroundService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.CompletableFuture;

public class PersonAddedEventHandlerTest {

    private PersonAddedEventHandler handler;

    private PersonBackgroundService personBackgroundService;;

    @BeforeEach
    void initialize(){
        this.personBackgroundService = Mockito.mock(PersonBackgroundService.class);
        handler = new PersonAddedEventHandler(personBackgroundService);
    }
    @Test
    void givenValidEventAndCleanDniThenSucced(){
        Mockito.when(personBackgroundService.getAntiterrorismStatus(Mockito.any()))
                .thenReturn(CompletableFuture.completedFuture(false));
        Mockito.when(personBackgroundService.getScore(Mockito.any()))
                .thenReturn(CompletableFuture.completedFuture(10.0f));
        Mockito.when(personBackgroundService.getBlacklistedStatus(Mockito.any()))
                .thenReturn(CompletableFuture.completedFuture(false));
        Assertions.assertDoesNotThrow(() -> handler.handle(personAddEvent()));
    }

    @Test
    void givenValidEventAndBannedDniThenSucced(){
        Mockito.when(personBackgroundService.getAntiterrorismStatus(Mockito.any()))
                .thenReturn(CompletableFuture.completedFuture(true));
        Mockito.when(personBackgroundService.getScore(Mockito.any()))
                .thenReturn(CompletableFuture.completedFuture(10.0f));
        Mockito.when(personBackgroundService.getBlacklistedStatus(Mockito.any()))
                .thenReturn(CompletableFuture.completedFuture(true));
        Assertions.assertDoesNotThrow(() -> handler.handle(personAddEvent()));
    }

    @Test
    void givenProviderIsDownThenFail(){
        Mockito.when(personBackgroundService.getAntiterrorismStatus(Mockito.any())).thenThrow(new NullPointerException());
        Mockito.when(personBackgroundService.getScore(Mockito.any()))
                .thenReturn(CompletableFuture.completedFuture(10.0f));
        Mockito.when(personBackgroundService.getBlacklistedStatus(Mockito.any()))
                .thenReturn(CompletableFuture.completedFuture(true));

        var event = personAddEvent();
        Assertions.assertDoesNotThrow(() -> handler.handle(event));
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
