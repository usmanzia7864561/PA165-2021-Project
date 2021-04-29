package muni.pa165.persistence.dao;

import muni.pa165.persistence.entity.Event;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


/**
 * Event DAO Implementitation
 *
 * @author Usman Zia
 */
@Repository
public class EventDaoImpl implements  EventDao {
    @PersistenceContext
    private EntityManager entityManager;
    private Object totalTime;

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

    @Override
    public List<Event> findByRange(LocalDate startDate, Optional<LocalDate> endDate) {
        if (endDate.isEmpty()){
            return this.entityManager.createQuery("select e from Event e where startDate=:startDate",Event.class).setParameter("startDate",startDate).getResultList();
        }else{
            return this.entityManager.createQuery("select e from Event e where startDate BETWEEN :startDate AND :endDate",Event.class).
                    setParameter("startDate",startDate)
                    .setParameter("endDate",endDate.get()).getResultList();
        }

    }
    @Override
    public List<Event> getTodayEvent() {
        LocalDate todayDate = LocalDate.now();
        return this.entityManager.createQuery("select e from Event e where eventDate=:todayDate",Event.class).setParameter("eventDate",todayDate).getResultList();
    }

    @Override
    public <Optional>List calculateParticipantEventTimeToday(Long userId) {
        LocalDate todayDate = LocalDate.now();
        return this.entityManager.createQuery("select SEC_TO_TIME(SUM(UNIX_TIMESTAMP(e.endTime) - UNIX_TIMESTAMP(e.startTime))) AS totalTime ,e from Event e where eventDate=:todayDate AND user=:userId").setParameter("eventDate",todayDate).setParameter("user",userId).getResultList();
    }

}
