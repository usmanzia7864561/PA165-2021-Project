package muni.pa165.services;

import muni.pa165.persistence.dao.CourtDao;
import muni.pa165.persistence.entity.Court;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class CourtServiceImpl implements CourtService{

    @Inject
    private CourtDao courtDao;



    @Override
    public void createCourt(Court c) {
        courtDao.create(c);
    }

    @Override
    public List<Court> getAllCourt() {return courtDao.findAll();}

    @Override
    public Optional<Court> findById(Long id) { return courtDao.findById(id); }

    @Override
    public void remove(Court court) { courtDao.remove(court); }


    @Override
    public List<Court> findByName(String name) {

        return courtDao.findByName(name);
    }
}
