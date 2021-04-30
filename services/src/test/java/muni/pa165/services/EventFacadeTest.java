package muni.pa165.services;

import muni.pa165.api.dto.EventDTO;
import muni.pa165.api.facade.EventFacade;
import muni.pa165.persistence.enums.EventType;
import muni.pa165.services.config.ServiceConfig;
import muni.pa165.services.facade.EventFacadeImpl;
import org.mockito.InjectMocks;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.LocalTime;

@ContextConfiguration(classes = ServiceConfig.class)
public class EventFacadeTest {
    @InjectMocks
    private EventFacade eventFacade;

    @BeforeClass
    public void setup(){
        eventFacade = new EventFacadeImpl();
    }

    @Test
    public void createEventTest(){
        EventDTO event = new EventDTO("New Event","dddd",LocalTime.NOON,LocalTime.MIDNIGHT, LocalDate.now(), EventType.TOURNAMENT);
        eventFacade.createEvent(event);
    }
}
