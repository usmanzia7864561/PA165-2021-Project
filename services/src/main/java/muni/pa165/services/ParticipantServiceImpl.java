package muni.pa165.services;

import muni.pa165.persistence.dao.ParticipantDao;
import muni.pa165.persistence.entity.Participant;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class ParticipantServiceImpl implements ParticipantService {
    @Inject
    private ParticipantDao participantDao;

    @Override
    public void create(Participant c) {
        participantDao.create(c);
    }

    @Override
    public List<Participant> getAllParticipants() {
        return participantDao.findAll();
    }

    @Override
    public Optional<Participant> findById(Long id) {
        return participantDao.findById(id);
    }

    @Override
    public void remove(Participant participant) {
        participantDao.remove(participant);
    }

    @Override
    public List<Participant> findByName(String name) {
        return participantDao.findByName(name);
    }
}
