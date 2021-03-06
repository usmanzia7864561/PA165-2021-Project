package muni.pa165.persistence.dao;

import muni.pa165.persistence.entity.Event;
import muni.pa165.persistence.entity.Participant;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDate;
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
    private Object totalTime;

    public EventDaoImpl() { }

    @Override
    public Optional<Event> create(Event event) {
        this.entityManager.persist(event);
        return this.findById(event.getId());
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
        this.entityManager.remove(this.entityManager.merge(event));
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
        return this.entityManager.createQuery("select SEC_TO_TIME(SUM(UNIX_TIMESTAMP(e.endTime) - UNIX_TIMESTAMP(e.startTime))) AS totalTime from Event e where eventDate=:todayDate AND participants=:participant").setParameter("eventDate",todayDate).setParameter("participant",userId).getResultList();
    }

    @Override
    public List<Event> findAllByCourt(long id) {
        return this.entityManager.createQuery("select e from Event e where court_id=:courtId", Event.class).setParameter("courtId",id).getResultList();
    }

    @Override
    public Participant addParticipant(long eventId, Participant participant) {
        Optional<Event> event = this.findById(eventId);
        if (event.isPresent()) {
            event.get().addParticipant(participant);

            System.out.println("Saving participant " + participant);
            this.entityManager.persist(participant);
        }
        return participant;
    }

}
