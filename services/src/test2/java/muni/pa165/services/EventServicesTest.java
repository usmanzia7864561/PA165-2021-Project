package muni.pa165.services;

import muni.pa165.persistence.dao.EventDao;
import muni.pa165.persistence.entity.Event;
import muni.pa165.persistence.enums.EventType;
import muni.pa165.services.config.ServiceConfig;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = ServiceConfig.class)
public class EventServicesTest {
    @Mock
    private EventDao eventDao;

    @Autowired
    @InjectMocks
    private EventService eventService;

    private Event event;

    public Event getEvent(){
        return new Event("ABC Tournament", LocalTime.NOON,LocalTime.MIDNIGHT, LocalDate.now(), EventType.TOURNAMENT);
    }


    @BeforeClass
    public void setup() throws ServiceException {
        eventService = new EventServiceImpl();
        event = getEvent();

        MockitoAnnotations.openMocks(this);
    }

    @BeforeMethod
    public void beforeMocks(){
        when(eventDao.create(any())).thenReturn(Optional.of(event));
        when(eventDao.findAll()).thenReturn(List.of(event));
        when(eventDao.findById(anyLong())).thenReturn(Optional.of(event));
        when(eventDao.findByName(anyString())).thenReturn(List.of(event));
        when(eventDao.findByRange(LocalDate.now(),Optional.empty())).thenReturn(List.of(event));
    }

    @Test
    public void createEventTest(){
        Optional<Event> e = eventService.createEvent(getEvent());

        Assert.assertTrue(e.isPresent());
        Assert.assertEquals(e, Optional.of(event));
    }

    @Test
    public void findEventByIdTest(){
        Optional<Event> eventById = eventService.getEventById(2L);

        Assert.assertTrue(eventById.isPresent());
        Assert.assertEquals(Optional.of(event), eventById);
    }

    @Test
    public void fetchAllEventsTest(){
        List<Event> events = eventService.getAllEvents();

        Assert.assertEquals(events.size(),1);
        Assert.assertTrue(events.contains(event));
    }

    @Test
    public void fetchAllByRangeEventsTest(){
        List<Event> events = eventService.getTodayEvents();

        Assert.assertEquals(events.size(),1);
        Assert.assertTrue(events.contains(event));
    }
}


