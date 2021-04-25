package muni.pa165.dao;

import muni.pa165.entity.Event;

import java.util.List;
import java.util.Optional;

/**
 * Event Data Access Object
 *
 * @author Usman Zia
 */
public interface EventDao {

    void create(Event event);

    List<Event> findAll();

    Optional<Event> findById(Long id);

    void remove(Event event);

    List<Event> findByName(String name) ;
}
