package muni.pa165.services.facade;

import muni.pa165.api.dto.ParticipantDTO;
import muni.pa165.api.facade.ParticipantFacade;
import muni.pa165.persistence.entity.Participant;
import muni.pa165.services.ParticipantService;
import muni.pa165.services.converter.DozerConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
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
    public ParticipantDTO createParticipant(ParticipantDTO c) {
        return null;
    }

    @Override
    public List<ParticipantDTO> getAllParticipants() {
        System.out.println("ALl participantService.getAllParticipants() " + participantService.getAllParticipants());
        return dozerConverter.convert(participantService.getAllParticipants(),ParticipantDTO.class);
    }

    @Override
    public void remove(ParticipantDTO participantDTO) {
        participantService.remove(dozerConverter.convert(participantDTO, Participant.class));
    }

    @Override
    public ParticipantDTO findById(Long id) {
        Optional<Participant> participant = participantService.findById(id);
        return participant.map(value -> dozerConverter.convert(value, ParticipantDTO.class)).orElse(null);
    }

    @Override
    public List<ParticipantDTO> getByEventParticipants(long eventId) {
        List<Participant> participants = participantService.getByEventParticipants(eventId);
        return dozerConverter.convert(participants, ParticipantDTO.class);
    }
}
