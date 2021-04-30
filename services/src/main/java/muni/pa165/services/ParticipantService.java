package muni.pa165.services;

import muni.pa165.persistence.entity.Participant;

import java.util.List;
import java.util.Optional;

public interface ParticipantService {
    void create(Participant c);

    /**
     * Get all registered Participant
     */
    List<Participant> getAllParticipants();

    /**
     * Find Participant by id
     * @param id
     * @return
     */
    Optional<Participant> findById(Long id);

    /**
     * remove Court
     */
    void remove(Participant court);

    /**
     * Find Participant by name
     * @param name
     * @return
     */
    List<Participant> findByName(String name);
}
