package muni.pa165.persistence.dao;

import muni.pa165.persistence.entity.Court;

import java.util.List;
import java.util.Optional;

/**
 * Court DAO Interface
 *
 * @author Dhruval patel
 */


public interface CourtDao
{
    /**
     * Create new court instance in database
     * @param court
     * @return
     */
    <Optional> java.util.Optional<Court> create(Court court);

    /**
     * Get all courts in database
     * @return
     */
    List<Court> findAll();

    /**
     * Find court by it's id
     * @param id
     * @return
     */
    Optional<Court> findById(Long id);

    /**
     * Remove court from database
     * @param court
     */
    void remove(Court court);

    /**
     * Find Court by it's name
     * @param name
     * @return
     */
    List<Court> findByName(String name) ;
}
