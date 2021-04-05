package muni.pa165.dao;

import muni.pa165.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class UserDaoImpl implements UserDao{
    private EntityManagerFactory emf;
    private EntityManager entityManager;

    public UserDaoImpl() {
        this.entityManager = emf.createEntityManager();
    }

    @Override
    public void create(User user) {
        this.entityManager.persist(user);
    }

    @Override
    public List<User> findAll() {
        return this.entityManager.createQuery("select u from user u",User.class).getResultList();
    }

    @Override
    public User findById(Long id) {
        return this.entityManager.find(User.class,id);
    }

    @Override
    public void remove(User user) {
        this.entityManager.remove(user);
    }

    @Override
    public List<User> findByName(String name) {
        return this.entityManager.createQuery("select u from user u where name=:name",User.class).setParameter("name",name).getResultList();
    }
}
