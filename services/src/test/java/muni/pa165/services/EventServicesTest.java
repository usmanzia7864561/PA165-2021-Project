import muni.pa165.persistence.dao.EventDao;
import muni.pa165.persistence.entity.Event;
import muni.pa165.persistence.entity.Participant;
import muni.pa165.persistence.entity.User;
import muni.pa165.persistence.enums.EventType;
import muni.pa165.services.EventService;
import muni.pa165.services.EventServiceImpl;
import muni.pa165.services.config.ServiceConfig;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ContextConfiguration(classes = ServiceConfig.class)
public class EventServicesTest {
    @Mock
    private EventDao eventDao;

    @Autowired
    @InjectMocks
    private EventService eventService;

    private Event event;
    private Event event1;
    private Event event2;
    private User tennisPlayer;

    private Participant p1;
    private Participant p2;

    public Event getEvent(){
        return new Event("ABC Tournament", LocalTime.NOON,LocalTime.MIDNIGHT, LocalDate.now(), EventType.TOURNAMENT);
    }


    @BeforeClass
    public void setup() throws ServiceException
    {
        eventService = new EventServiceImpl();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void CreateEvent(){
        event = eventService.createEvent(getEvent());
        Assert.assertNotNull(event);
    }
    @Test
    public void addParticipantToEvent(){
        event1 = new Event("ABC Tournament 1", LocalTime.NOON,LocalTime.MIDNIGHT, LocalDate.now(), EventType.TOURNAMENT);
        event2 = new Event("ABC Tournament 2", LocalTime.NOON,LocalTime.MIDNIGHT, LocalDate.now(), EventType.TENNIS_USER);

        p1 = new Participant("Usman");
        p2 = new Participant("Robert");
    }

//    @Test(expectedExceptions = {ValidationException.class})
//    public void registerEventWithoutName() throws ValidationException{
//        event = new Event("", LocalTime.NOON,LocalTime.MIDNIGHT, LocalDate.now(), EventType.TENNIS_USER);
//        eventService.createEvent(event);
//    }

//    @Test(expectedExceptions = {ValidationException.class})
//    public void registerEventWithoutStartTime() throws ValidationException{
//        event = new Event("New Year Event",null,LocalTime.MIDNIGHT, LocalDate.now(), EventType.TOURNAMENT);
//        eventService.createEvent(event);
//    }
//
//    @Test(expectedExceptions = {ValidationException.class})
//    public void registerEventWithoutEndTime() throws ValidationException{
//        event = new Event("New Year Event",LocalTime.NOON,null, LocalDate.now(), EventType.TOURNAMENT);
//        eventService.createEvent(event);
//    }
//
//    @Test(expectedExceptions = {ValidationException.class})
//    public void registerEventWithoutDate() throws ValidationException{
//        event = new Event("New Year Event",LocalTime.NOON,LocalTime.MIDNIGHT, null, EventType.TOURNAMENT);
//        eventService.createEvent(event);
//    }
//
//    @Test(expectedExceptions = {ValidationException.class})
//    public void registerEventWithoutEventType() throws ValidationException{
//        event = new Event("New Year Event",LocalTime.NOON,LocalTime.MIDNIGHT, LocalDate.now(), null);
//        eventService.createEvent(event);
//    }

    @Test
    public void findEventByIdTest(){
        when(eventService.getEventById(event.getId())).thenReturn(Optional.of(getEvent()));

        Optional<Event> eventById = eventService.getEventById(event.getId());


        Assert.assertTrue(eventById.isPresent());
        Assert.assertEquals(event.getId(), eventById.get().getId());
    }

}


