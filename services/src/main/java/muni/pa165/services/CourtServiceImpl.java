package muni.pa165.services;

import muni.pa165.persistence.dao.CourtDao;
import muni.pa165.persistence.entity.Court;
import muni.pa165.persistence.entity.Event;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Service
public class CourtServiceImpl implements CourtService{

    @Inject
    private CourtDao courtDao;
    private String name;

    @Override
    public void registerCourt(Court c) {
        courtDao.create(c);
    }

    @Override
    public List<Court> GetAllCourt() {return courtDao.findAll();}

    @Override
    public Optional<Court> findById(Long id) { return courtDao.findById(id); }

    @Override
    public void remove(Court court) { courtDao.remove(court); }

    @Override
    public List<Court> findByName(String name) {
        this.name = name;
        return courtDao.findByName(name);
    }

    @Override
    public Optional<Event> addEventToCourt(long courtId, Event event) {
        System.out.println("addEventToCourt : " + event);
        return courtDao.addEventToCourt(courtId, event);
    }
}
