package muni.pa165.persistence.dao;

import muni.pa165.persistence.entity.User;
import muni.pa165.persistence.enums.UserType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * User Data access object implementation
 *
 * @author Muhammad Abdullah
 */

@Repository
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
    public Optional<User> findById(Long id)
    {
        try{
            return Optional.ofNullable(this.entityManager.find(User.class,id));
        }catch (NoResultException exception){
            return Optional.empty();
        }
    }

    @Override
    public List<User> findByType(UserType type){
        return this.entityManager.createQuery("select u from User u where type=:type",User.class).setParameter("type",type).getResultList();
    }

    @Override
    public void remove(User user) {
        this.entityManager.remove(user);
    }

    @Override
    public List<User> findByName(String name) {
        return this.entityManager.createQuery("select u from User u where name=:name",User.class).setParameter("name",name).getResultList();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }
}
