package muni.pa165.dao;

import muni.pa165.entity.Court;


import java.util.List;

/**
 * Court DAO Interface
 *
 * @author Muhammad Usman
 */


public interface CourtDao
{
    void create(Court court);

    List<Court> findAll();

    Court findById(Long id);

    void remove(Court court);

    List<Court> findByName(String name) ;


}
