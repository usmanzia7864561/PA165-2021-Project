package muni.pa165.api.facade;

import muni.pa165.api.dto.ParticipantDTO;

import java.util.Collection;

/**
 * ParticipantFacade interfaces for all possible methods for it's implementation
 */
public interface ParticipantFacade {
    /**
     * Find Participant By Name
     * @return
     */
    Collection<ParticipantDTO> findByName(String name);

    /**
     * Register Participant
     */
    void createParticipant(ParticipantDTO c);

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
}
