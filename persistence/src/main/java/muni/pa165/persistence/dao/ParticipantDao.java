package muni.pa165.persistence.dao;

import muni.pa165.persistence.entity.Participant;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/*
 * Participant Data access object interface
 *
 * @author Muhammad Abdullah
 */

@Repository
public interface ParticipantDao {
    /**
     * create new participant
     *
     * @param participant
     */
    Optional<Participant> create(Participant participant);

    /**
     * Get all participants in the database
     *
     * @return
     */
    List<Participant> findAll();

    /**
     * Get a participant by it's id
     *
     * @param id
     * @return
     */
    Optional<Participant> findById(Long id);

    /**
     * Remove a specific participant for database
     *
     * @param participant
     */
    void remove(Participant participant);

    /**
     * Get a participant by it's name
     *
     * @param name
     * @return
     */
    List<Participant> findByName(String name) ;
}
