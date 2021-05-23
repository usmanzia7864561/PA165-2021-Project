package muni.pa165.api.facade;

import muni.pa165.api.dto.EventDTO;
import muni.pa165.api.dto.ParticipantDTO;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author Usman Zia
 */
@Service
public interface EventFacade {


    EventDTO findEventById(Long id);

    /**
     * Get all Event
     */
    Collection<EventDTO> getAllEvents();

    /**
     * Get all Event of a specific court
     */
    Collection<EventDTO> getAllCourtEvents(long id);

    /**
     * Get today Event
     */
    Collection<EventDTO> getTodayEvents();

    /**
     * Create the new Event.
     */
    EventDTO createEvent(EventDTO eventDTO);

    /**
     * Add new participant.
     */
    ParticipantDTO addParticipant(long eventId, ParticipantDTO participantDTO);

    boolean remove(long id);
}


