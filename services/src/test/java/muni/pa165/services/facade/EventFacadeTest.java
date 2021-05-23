package muni.pa165.services.facade;

import muni.pa165.api.dto.EventDTO;
import muni.pa165.api.facade.EventFacade;
import muni.pa165.persistence.entity.Event;
import muni.pa165.persistence.enums.EventType;
import muni.pa165.services.EventService;
import muni.pa165.services.config.ServiceConfig;
import muni.pa165.services.converter.DozerConverter;
import muni.pa165.services.converter.DozerConverterImpl;
import org.mockito.InjectMocks;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = ServiceConfig.class)
public class EventFacadeTest {
    @InjectMocks
    private EventFacade eventFacade;

    @Inject
    private DozerConverter converter;

    EventService eventService = mock(EventService.class);

    EventDTO eventDTO = new EventDTO("Tennis Tournament","No Description",LocalTime.NOON.toString(),LocalTime.MIDNIGHT.toString(),LocalDate.now().toString(),EventType.TOURNAMENT, Set.of());
    Event event;

    @Ignore
    @BeforeClass
    public void setup(){
        converter = new DozerConverterImpl();
        event = converter.convert(eventDTO,Event.class);
        eventFacade = new EventFacadeImpl(eventService,converter);
    }

    @BeforeMethod
    public void beforeMocks(){
        when(eventService.createEvent(any())).thenReturn(Optional.of(event));
        when(eventService.getAllEvents()).thenReturn(List.of(event));
        when(eventService.getEventById(anyLong())).thenReturn(Optional.of(event));
    }

    @Test
    public void getAllEventTest(){
        Collection<EventDTO> events = eventFacade.getAllEvents();
        Assert.assertEquals(events.size(),1);
    }
}
