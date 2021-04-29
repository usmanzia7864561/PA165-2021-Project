package muni.pa165.services.facade;



import muni.pa165.api.facade.CourtFacade;
import muni.pa165.api.dto.CourtDTO;
import muni.pa165.persistence.dao.CourtDao;
import muni.pa165.persistence.entity.Court;

import muni.pa165.services.BeanMappingService;
import muni.pa165.services.CourtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Service
public class CourtFacedImpl implements CourtFacade {

    @Autowired
    private CourtService courtService;

    @Inject
    private CourtDao courtDao;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public Object findByName(String name) {

        try {
            return courtDao.findByName(name);
        }catch (NoResultException e){
            return Optional.empty();
        }
    }

    @Override
    public void createCourt(CourtDTO c) {
        Court courtEntity = beanMappingService.mapTo(c, Court.class);
        courtService.createCourt(courtEntity);
        c.setId(courtEntity.getId());
    }




    @Override
    public List<Court> getAllCourtDTO() {
        return courtDao.findAll();
    }



    @Override
    public void remove(Court court) {

        courtService.remove(court);

    }

    @Override
    public Optional<Court> findById(Long id) {
        Optional<Court> court = courtService.findById(id);
        return court;
    }
}
