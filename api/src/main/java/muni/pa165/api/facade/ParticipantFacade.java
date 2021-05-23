package muni.pa165.api.facade;

import muni.pa165.api.dto.ParticipantDTO;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * ParticipantFacade interfaces for all possible methods for it's implementation
 */

@Service
public interface ParticipantFacade {
    /**
     * Find Participant By Name
     * @return
     */
    Collection<ParticipantDTO> findByName(String name);

    /**
     * Register Participant
     * @return
     */
    ParticipantDTO createParticipant(ParticipantDTO c);

    /**
     * Get All Participants
     */
    Collection<ParticipantDTO> getAllParticipants();

    /**
     * Remove Participant
     */
    void remove(ParticipantDTO participantDTO);

    /**
     * Find Participant by id
     * @return
     */
    ParticipantDTO findById(Long id);

    List<ParticipantDTO> getByEventParticipants(long eventId);
}
