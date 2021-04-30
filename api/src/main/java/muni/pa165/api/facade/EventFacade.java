package muni.pa165.api.facade;

import muni.pa165.api.dto.EventDTO;

import java.util.Collection;

/**
 * @author Usman Zia
 */
public interface EventFacade {


    EventDTO findEventById(Long id);

    /**
     * Get all Event
     */
    Collection<EventDTO> getAllEvents();

    /**
     * Get today Event
     */
    Collection<EventDTO> getTodayEvents();

    /**
     * Create the new Event.
     */
    void createEvent(EventDTO eventDTO);
}


