package muni.pa165.services;

import muni.pa165.persistence.dao.EventDao;
import muni.pa165.persistence.entity.Event;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class EventServiceImpl  implements EventService {

    @Inject
    private EventDao eventDao;

    @Override
    public void createEvent(Event event) {
        eventDao.create(event);
    }


    @Override
    public List<Event> getAllEvents() {
        return eventDao.findAll();
    }

    @Override
    public List<Event> getTodayEvents() {
        return  eventDao.findByRange(LocalDate.now(), Optional.empty());
    }

    @Override
    public void removeEvent(Event event) {
        eventDao.remove(event);
    }
}
