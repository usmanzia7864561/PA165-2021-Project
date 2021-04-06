package muni.pa165.dao;

import muni.pa165.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * User Data access object implementation
 *
 * @author Muhammad Abdullah
 */

@Repository
@Transactional
public class UserDaoImpl implements UserDao{
    @PersistenceContext
    private EntityManager entityManager;

    public UserDaoImpl() { }

    @Override
    public void create(User user) {
        this.entityManager.persist(user);
    }

    @Override
    public List<User> findAll() {
        return this.entityManager.createQuery("select u from User u",User.class).getResultList();
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
        return this.entityManager.createQuery("select u from User u where name=:name",User.class).setParameter("name",name).getResultList();
    }
}
