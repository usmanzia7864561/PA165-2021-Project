package muni.pa165.dao;

import muni.pa165.entity.Event;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


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
    public Optional<Event> findById(Long id) {
        try{
            return Optional.ofNullable(this.entityManager.find(Event.class,id));
        }catch (NoResultException exception){
            return Optional.empty();
        }
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
