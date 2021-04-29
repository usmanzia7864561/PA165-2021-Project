package muni.pa165.persistence.dao;

import muni.pa165.persistence.entity.Event;

import java.time.LocalDate;
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

    List<Event> findByRange(LocalDate startDate, Optional<LocalDate> endDate) ;

    List<Event> getTodayEvent();

    <Optional>List calculateParticipantEventTimeToday(Long id);
}
