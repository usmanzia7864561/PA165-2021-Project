package muni.pa165.services.facade;

import muni.pa165.api.dto.EventDTO;
import muni.pa165.api.facade.EventFacade;
import muni.pa165.persistence.entity.Event;
import muni.pa165.services.EventService;
import muni.pa165.services.converter.DozerConverter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class EventFacadeImpl implements EventFacade {

    @Autowired
    private EventService eventService;

    @Inject
    private DozerConverter dozerConverter;

    @Override
    public EventDTO findEventById(Long eventId) {
        Optional<Event> event = eventService.getEventById(eventId);
        return event.isEmpty() ? null : dozerConverter.convert((Object) event, EventDTO.class);
    }

    @Override
    public void createEvent(EventDTO u) {
        Event eventEntity = dozerConverter.convert(u, Event.class);
        eventService.createEvent(eventEntity);
    }

    @Override
    public Collection<EventDTO> getAllEvents() {
        List<Event> event = eventService.getAllEvents();
        return event.isEmpty() ? null : dozerConverter.convert((Collection<?>) event, EventDTO.class);
    }

    @Override
    public Collection<EventDTO> getTodayEvents() {
        List<Event> event = eventService.getTodayEvents();
        return event.isEmpty() ? null : dozerConverter.convert((Collection<?>) event, EventDTO.class);
    }

}
