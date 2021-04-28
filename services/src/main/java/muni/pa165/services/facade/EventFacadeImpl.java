package muni.pa165.services.facade;

import muni.pa165.api.dto.EventDTO;
import muni.pa165.api.facade.EventFacade;
import muni.pa165.persistence.entity.Event;
import muni.pa165.services.BeanMappingService;
import muni.pa165.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

public class EventFacadeImpl implements EventFacade {

    @Autowired
    private EventService eventService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public EventDTO findEventById(Long eventId) {
        List<Event> event = eventService.getEventById(eventId);
        return event.isEmpty() ? null : beanMappingService.mapTo((Object) event, EventDTO.class);
    }

    @Override
    public Collection<EventDTO> getAllEvents() {
        List<Event> event = eventService.getAllEvents();
        return event.isEmpty() ? null : beanMappingService.mapTo((Collection<?>) event, EventDTO.class);
    }

    @Override
    public Collection<EventDTO> getTodayEvents() {
        List<Event> event = eventService.getTodayEvents();
        return event.isEmpty() ? null : beanMappingService.mapTo((Collection<?>) event, EventDTO.class);
    }

}
