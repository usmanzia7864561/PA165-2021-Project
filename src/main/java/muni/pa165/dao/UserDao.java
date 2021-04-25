package muni.pa165.dao;

import muni.pa165.entity.User;
import muni.pa165.enums.UserType;

import java.util.List;

import java.util.Optional;
/**
 * User Data access object interface
 *
 * @author Muhammad Abdullah
 */

public interface UserDao {

    void create(User user);

    List<User> findAll();

    Optional<User> findById(Long id);

    List<User> findByType(UserType type);

    void remove(User user);

    List<User> findByName(String name) ;

}
