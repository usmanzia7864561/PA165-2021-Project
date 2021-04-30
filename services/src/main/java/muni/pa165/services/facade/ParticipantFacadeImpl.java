package muni.pa165.services.facade;

import muni.pa165.api.dto.ParticipantDTO;
import muni.pa165.api.facade.ParticipantFacade;
import muni.pa165.persistence.entity.Participant;
import muni.pa165.services.ParticipantService;
import muni.pa165.services.converter.DozerConverter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import java.util.Collection;

public class ParticipantFacadeImpl implements ParticipantFacade {
    @Autowired
    private ParticipantService participantService;

    @Inject
    private DozerConverter dozerConverter;

    public ParticipantFacadeImpl() { }

    public ParticipantFacadeImpl(ParticipantService ps, DozerConverter converter) {
        participantService = ps;
        dozerConverter = converter;
    }

    @Override
    public Collection<ParticipantDTO> findByName(String name) {
        return dozerConverter.convert(participantService.findByName(name),ParticipantDTO.class);
    }

    @Override
    public void createParticipant(ParticipantDTO participantDTO) {
        participantService.create(dozerConverter.convert(participantDTO, Participant.class));
    }

    @Override
    public Collection<ParticipantDTO> getAllParticipants() {
        return dozerConverter.convert(participantService.getAllParticipants(),ParticipantDTO.class);
    }

    @Override
    public void remove(ParticipantDTO participantDTO) {
        participantService.remove(dozerConverter.convert(participantDTO, Participant.class));
    }

    @Override
    public ParticipantDTO findById(Long id) {
        return dozerConverter.convert(participantService.findById(id),ParticipantDTO.class);
    }
}
