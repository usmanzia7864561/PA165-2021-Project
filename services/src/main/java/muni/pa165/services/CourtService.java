package muni.pa165.services;

import muni.pa165.persistence.entity.Court;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CourtService {

    void createCourt(Court c);

    /**
     * Get all registered Court
     */
    List<Court> getAllCourt();


    Optional<Court> findById(Long id);

    /**
     * remove Court
     */
    void remove(Court court);


    List<Court> findByName(String name);


  //  Object GetAllCourt();
}
