package muni.pa165.dao;

import muni.pa165.entity.Court;


import java.util.List;

/**
 * Court DAO Implementation
 *
 * @author Muhammad Usman
 */
@Repository
@Transactional
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
    public Court findById(Long id) {
        return this.entityManager.find(Court.class,id);
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
