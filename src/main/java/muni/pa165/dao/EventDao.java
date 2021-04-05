package muni.pa165.dao;

import muni.pa165.entity.Event;

import java.util.List;

public interface EventDao {

    void create(Event event);

    List<Event> findAll();

    Event findById(Long id);

    void remove(Event event);

    List<Event> findByName(String name) ;
}
