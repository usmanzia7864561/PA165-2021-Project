package muni.pa165.services;

import muni.pa165.persistence.dao.EventDao;
import muni.pa165.persistence.entity.Event;
import muni.pa165.persistence.entity.Participant;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class EventServiceImpl  implements EventService {
    @Inject
    private EventDao eventDao;

    @Override
    public Optional<Event> createEvent(Event event) {
        return eventDao.create(event);
    }


    @Override
    public List<Event> getAllEvents() {
        return eventDao.findAll();
    }

    @Override
    public Optional<Event> getEventById(Long eventId) {
        return eventDao.findById(eventId);
    }

    @Override
    public List<Event> getTodayEvents() {
        return  eventDao.findByRange(LocalDate.now(), Optional.empty());
    }

    @Override
    public void removeEvent(Event event) {
        eventDao.remove(event);
    }

    @Override
    public boolean participantAvailability(Participant p)
    {
        List participantEventInfo = eventDao.calculateParticipantEventTimeToday(p.getId());
        Integer totalTime = Integer.parseInt(participantEventInfo.get(0).toString());
        return totalTime <= 2;
    }
}
