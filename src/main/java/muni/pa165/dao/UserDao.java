package muni.pa165.dao;

import muni.pa165.entity.User;

import java.util.List;

/**
 * User Data access object interface
 *
 * @author Muhammad Abdullah
 */

public interface UserDao {

    void create(User user);

    List<User> findAll();

    User findById(Long id);

    void remove(User user);

    List<User> findByName(String name) ;

}
