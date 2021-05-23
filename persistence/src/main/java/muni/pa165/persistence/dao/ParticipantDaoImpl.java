package muni.pa165.persistence.dao;

import muni.pa165.persistence.entity.Event;
import muni.pa165.persistence.entity.Participant;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/*
 * Participant Data access object implementation
 *
 * @author Muhammad Abdullah
 */

@Repository
@Transactional
public class ParticipantDaoImpl implements ParticipantDao {
    @PersistenceContext
    private EntityManager entityManager;

    public ParticipantDaoImpl() {
    }

    @Override
    public Optional<Participant> create(Participant participant) {
        this.entityManager.persist(participant);
        return this.findById(participant.getId());
    }

    @Override
    public List<Participant> findAll() {
        return this.entityManager.createQuery("select p from Participant p", Participant.class).getResultList();
    }

    @Override
    public Optional<Participant> findById(Long id) {
        try {
            return Optional.ofNullable(this.entityManager.find(Participant.class, id));
        } catch (NoResultException exception) {
            return Optional.empty();
        }
    }

    @Override
    public void remove(Participant participant) {
        this.entityManager.remove(participant);
    }

    @Override
    public List<Participant> findByName(String name) {
        return this.entityManager.createQuery("select p from Participant p where name=:name", Participant.class).setParameter("name", name).getResultList();
    }

    @Override
    public List<Participant> getByEventParticipants(long eventId) {
        return List.copyOf(this.entityManager.find(Event.class,eventId).getParticipants());
    }
}
