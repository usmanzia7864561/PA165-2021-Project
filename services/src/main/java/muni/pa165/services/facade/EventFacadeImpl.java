package muni.pa165.services.facade;

import muni.pa165.api.dto.EventDTO;
import muni.pa165.api.dto.ParticipantDTO;
import muni.pa165.api.facade.EventFacade;
import muni.pa165.persistence.entity.Event;
import muni.pa165.persistence.entity.Participant;
import muni.pa165.services.EventService;
import muni.pa165.services.converter.DozerConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class EventFacadeImpl implements EventFacade {
    @Autowired
    private EventService eventService;

    @Inject
    private DozerConverter dozerConverter;

    public EventFacadeImpl() {
    }

    public EventFacadeImpl(EventService eventService, DozerConverter dozerConverter) {
        this.eventService = eventService;
        this.dozerConverter = dozerConverter;
    }

    @Override
    public EventDTO findEventById(Long eventId) {
        Optional<Event> event = eventService.getEventById(eventId);
        return event.map(value -> dozerConverter.convert(value, EventDTO.class)).orElse(null);
    }

    @Override
    public EventDTO createEvent(EventDTO eventDTO) {
        Event ev = dozerConverter.convert(eventDTO, Event.class);
        System.out.println("add event 1" + eventDTO.toString());
        System.out.println("Add event " + ev.toString());
        eventService.createEvent(ev);
        return eventDTO;
    }

    @Override
    public ParticipantDTO addParticipant(long eventId, ParticipantDTO participantDTO) {
        Participant participant = eventService.addParticipant(eventId, dozerConverter.convert(participantDTO, Participant.class));
        return dozerConverter.convert(participant, ParticipantDTO.class);
    }

    @Override
    public boolean remove(long id) {
        Optional<Event> event = eventService.getEventById(id);
        return event.map(value -> {
            eventService.removeEvent(value);
            return true;
        }).orElse(false);
    }

    @Override
    public Collection<EventDTO> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return dozerConverter.convert(events, EventDTO.class);
    }

    @Override
    public Collection<EventDTO> getAllCourtEvents(long id) {
        List<Event> events = eventService.getAllCourtEvents(id);
        return dozerConverter.convert(events, EventDTO.class);
    }

    @Override
    public Collection<EventDTO> getTodayEvents() {
        List<Event> events = eventService.getTodayEvents();
        return dozerConverter.convert(events, EventDTO.class);
    }

}
