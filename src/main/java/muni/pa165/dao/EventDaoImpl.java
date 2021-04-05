package muni.pa165.dao;

import muni.pa165.entity.Event;
import muni.pa165.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class EventDaoImpl implements  EventDao {

    private EntityManagerFactory emf;
    private EntityManager entityManager;

    public EventDaoImpl() {
        this.entityManager = emf.createEntityManager();
    }

    @Override
    public void create(Event event) {
        this.entityManager.persist(event);
    }

    @Override
    public List<Event> findAll() {
        return this.entityManager.createQuery("select e from Event e",Event.class).getResultList();
    }

    @Override
    public Event findById(Long id) {
        return this.entityManager.find(Event.class,id);
    }

    @Override
    public void remove(Event event) {
        this.entityManager.remove(event);
    }

    @Override
    public List<Event> findByName(String name) {
        return this.entityManager.createQuery("select e from Event e where name=:name",Event.class).setParameter("name",name).getResultList();
    }
}
