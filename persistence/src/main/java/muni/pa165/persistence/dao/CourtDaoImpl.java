package muni.pa165.persistence.dao;

import muni.pa165.persistence.entity.Court;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * Court DAO Implementation
 *
 * @author Muhammad Usman
 */
@Repository
public class CourtDaoImpl implements CourtDao
{
    @PersistenceContext
    private EntityManager entityManager;

    public CourtDaoImpl() { }

    @Override
    public void create(Court court) {
        this.entityManager.persist(court);
    }

    @Override
    public List<Court> findAll() {
        return this.entityManager.createQuery("select c from Court c", Court.class).getResultList();
    }

    @Override
    public Optional<Court> findById(Long id) {
        try{
            return Optional.ofNullable(this.entityManager.find(Court.class,id));
        }catch (NoResultException noResultException){
            return Optional.empty();
        }
    }

    @Override
    public void remove(Court court) {
        this.entityManager.remove(court);
    }

    @Override
    public List<Court> findByName(String name) {
        return this.entityManager.createQuery("select c from Court c where name=:name",Court.class).setParameter("name",name).getResultList();
    }
}
