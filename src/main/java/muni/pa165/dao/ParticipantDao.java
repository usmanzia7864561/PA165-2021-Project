package muni.pa165.dao;

import muni.pa165.entity.Participant;
import java.util.List;

/*
 * Participant Data access object interface
 *
 * @author Muhammad Abdullah
 */

public interface ParticipantDao {
    /**
     * create new participant
     *
     * @param participant
     */
    void create(Participant participant);

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
    Participant findById(Long id);

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
