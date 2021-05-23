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
public class CourtFacedImpl implements CourtFacade {

    @Autowired
    private CourtService courtService;

    @Autowired
    private DozerConverter dozerConverter;

    @Override
    public CourtDTO findByName(String name) {

       List<Court> court = courtService.findByName(name);
       return court.isEmpty() ? null : dozerConverter.convert((Object) court, CourtDTO.class);

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
        List<CourtDTO> courtDTOS = dozerConverter.convert(courtService.GetAllCourt(), CourtDTO.class);
        return courtDTOS;
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
