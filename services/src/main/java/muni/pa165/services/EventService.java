package muni.pa165.services;

import muni.pa165.persistence.entity.Event;
import muni.pa165.persistence.entity.Participant;

import java.util.List;
import java.util.Optional;

public interface EventService {

    /**
     * Add the event.
     */
    void createEvent(Event event);


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
    boolean ParticipantAvailibility(Participant p);

}
