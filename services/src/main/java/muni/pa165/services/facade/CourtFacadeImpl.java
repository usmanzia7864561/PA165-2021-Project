package muni.pa165.services.facade;



import muni.pa165.api.facade.CourtFacade;
import muni.pa165.api.dto.CourtDTO;
import muni.pa165.persistence.entity.Court;


import muni.pa165.services.converter.DozerConverter;
import muni.pa165.services.CourtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class CourtFacadeImpl implements CourtFacade {
    @Autowired
    private CourtService courtService;

    @Autowired
    private DozerConverter dozerConverter;

    @Override
    public List<CourtDTO> findByName(String name) {
       List<Court> court = courtService.findByName(name);
       return dozerConverter.convert(court, CourtDTO.class);
    }

    @Override
    public CourtDTO registerCourt(CourtDTO c) {
        Court courtEntity = dozerConverter.convert(c, Court.class);
        courtService.registerCourt(courtEntity);
        c.setId(courtEntity.getId());
        return  c;
    }

    @Override
    public Collection<CourtDTO> getAllCourtDTO() {
        return dozerConverter.convert(courtService.GetAllCourt(), CourtDTO.class);
    }

    @Override
    public void remove(CourtDTO court) {
        courtService.remove(dozerConverter.convert(court,Court.class));
    }

    @Override
    public CourtDTO findById(Long id) {
        Optional<Court> court = courtService.findById(id);
        return court.map(value -> dozerConverter.convert(value, CourtDTO.class)).orElse(null);
    }
}
