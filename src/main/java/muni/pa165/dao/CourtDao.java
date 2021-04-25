package muni.pa165.dao;

import muni.pa165.entity.Court;


import java.util.List;
import java.util.Optional;

/**
 * Court DAO Interface
 *
 * @author Muhammad Usman
 */


public interface CourtDao
{
    /**
     * Create new court instance in database
     * @param court
     */
    void create(Court court);

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
