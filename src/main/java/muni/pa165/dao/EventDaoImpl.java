package muni.pa165.dao;

import muni.pa165.entity.Event;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;


/**
 * Event DAO Implementitation
 *
 * @author Usman Zia
 */
@Repository
@Transactional
public class EventDaoImpl implements  EventDao {
    @PersistenceContext
    private EntityManager entityManager;

    public EventDaoImpl() { }

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
