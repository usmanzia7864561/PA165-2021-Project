package muni.pa165.services;

import muni.pa165.persistence.entity.Event;

import java.util.List;

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
    List<Event> getEventById(Long id);

    /**
     * Get today events
     */
    List<Event> getTodayEvents();

    /**
     * Delete the event.
     */
    void removeEvent(Event event);

}
