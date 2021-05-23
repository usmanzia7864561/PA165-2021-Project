package muni.pa165.services;

import muni.pa165.persistence.entity.Event;
import muni.pa165.persistence.entity.Participant;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EventService {

    /**
     * Add the event.
     * @return
     */
    Optional<Event> createEvent(Event event);


    /**
     * Get all events
     */
    List<Event> getAllEvents();


    /**
     * Get  events by id
     */
    Optional<Event> getEventById(Long id);

    /**
     * Get today events
     */
    List<Event> getTodayEvents();

    /**
     * Delete the event.
     */
    void removeEvent(Event event);

    /**
     * Check if the given user that access all the event is admin.
     */
    boolean participantAvailability(Participant p);

    List<Event> getAllCourtEvents(long id);
}
