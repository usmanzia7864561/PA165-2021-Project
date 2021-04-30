package muni.pa165.api.facade;
import muni.pa165.api.dto.CourtDTO;

import muni.pa165.persistence.entity.Court;

import java.util.Collection;
import java.util.Optional;

public interface CourtFacade {
    /**
     * Find Court By Name
     * @return
     */
    Object findByName(String name);
    /**
     * Register Court
     */
    void createCourt(CourtDTO c);
    /**
     * Get All court
     * @return
     */
    Collection<CourtDTO> getAllCourtDTO();
    /**
     * Remove Court
     */
    void remove(Court court);
    /**
     * find Court by id
     */
    Optional<Court> findById(Long id);
}
