package muni.pa165.persistence.dao;

import muni.pa165.persistence.entity.User;
import muni.pa165.persistence.enums.UserType;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.Optional;
/**
 * User Data access object interface
 *
 * @author Muhammad Abdullah
 */

@Repository
public interface UserDao {

    void create(User user);

    List<User> findAll();

    Optional<User> findById(Long id);

    List<User> findByType(UserType type);

    void remove(User user);

    List<User> findByName(String name) ;

    Optional<User> findByEmail(String email) ;
}
