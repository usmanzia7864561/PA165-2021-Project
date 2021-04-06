package muni.pa165.dao;

import muni.pa165.entity.Participant;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ParticipantDaoImpl implements ParticipantDao {
    @PersistenceContext
    private EntityManager entityManager;

    public ParticipantDaoImpl() { }

    @Override
    public void create(Participant participant) {
        this.entityManager.persist(participant);
    }

    @Override
    public List<Participant> findAll() {
        return this.entityManager.createQuery("select u from User u",Participant.class).getResultList();
    }

    @Override
    public Participant findById(Long id) {
        return this.entityManager.find(Participant.class,id);
    }

    @Override
    public void remove(Participant participant) {
        this.entityManager.remove(participant);
    }

    @Override
    public List<Participant> findByName(String name) {
        return this.entityManager.createQuery("select u from User u where name=:name",Participant.class).setParameter("name",name).getResultList();
    }
}
