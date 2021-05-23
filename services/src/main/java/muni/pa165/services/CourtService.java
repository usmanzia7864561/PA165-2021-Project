package muni.pa165.services;

import muni.pa165.persistence.entity.Court;
import muni.pa165.persistence.entity.Event;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CourtService {

    void registerCourt(Court c);

    /**
     * Get all registered Court
     */
    List<Court> GetAllCourt();


    Optional<Court> findById(Long id);

    /**
     * remove Court
     */
    void remove(Court court);


    List<Court> findByName(String name);


    Optional<Event> addEventToCourt(long courtId, Event event);
}
