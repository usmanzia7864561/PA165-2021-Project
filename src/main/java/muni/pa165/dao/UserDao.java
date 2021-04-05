package muni.pa165.dao;

import muni.pa165.entity.User;

import java.util.List;

/**
 * Created by Muhammad Abdullah on 05.04.2021.
 */

public interface UserDao {

    void create(User user);

    List<User> findAll();

    User findById(Long id);

    void remove(User user);

    List<User> findByName(String name) ;

}
