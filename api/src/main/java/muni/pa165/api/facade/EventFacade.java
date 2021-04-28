package muni.pa165.api.facade;

import muni.pa165.api.dto.EventDTO;

import java.util.Collection;

public interface EventFacade {


    EventDTO findEventById(Long userId);

    /**
     * Get all Event
     */
    Collection<EventDTO> getAllEvents();

    /**
     * Get today Event
     */
    Collection<EventDTO> getTodayEvents();
}


