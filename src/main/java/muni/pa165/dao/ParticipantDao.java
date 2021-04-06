package muni.pa165.dao;

import muni.pa165.entity.Participant;
import java.util.List;

public interface ParticipantDao {
    void create(Participant participant);
    List<Participant> findAll();
    Participant findById(Long id);
    void remove(Participant participant);
    List<Participant> findByName(String name) ;
}
