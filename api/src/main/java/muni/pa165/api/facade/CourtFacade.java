package muni.pa165.api.facade;
import muni.pa165.api.dto.CourtDTO;

import muni.pa165.persistence.entity.Court;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
@Service
public interface CourtFacade {
    /**
     * Find Court By Name
     * @return
     */
    List<CourtDTO> findByName(String name);
    /**
     * Register Court
     */
    CourtDTO registerCourt(CourtDTO c);
    /**
     * Get All court
     */
    Collection<CourtDTO> getAllCourtDTO();
    /**
     * Remove Court
     */
    void remove(CourtDTO court);
    /**
     * find Court by id
     */
    CourtDTO findById(Long id);
}
