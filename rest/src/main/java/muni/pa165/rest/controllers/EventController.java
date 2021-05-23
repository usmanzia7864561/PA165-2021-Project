package muni.pa165.rest.controllers;

import muni.pa165.api.dto.CourtDTO;
import muni.pa165.api.dto.EventDTO;
import muni.pa165.api.facade.CourtFacade;
import muni.pa165.api.facade.EventFacade;
import muni.pa165.persistence.entity.Court;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    EventFacade eventFacade;

    @GetMapping(value = "/")
    Collection<EventDTO> index(){
        return eventFacade.getAllEvents();
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public  EventDTO fetchById(@PathVariable long id){
        return eventFacade.findEventById(id);
    }

    @PostMapping(value = "/add",produces = MediaType.APPLICATION_JSON_VALUE)
    public EventDTO add(@RequestBody EventDTO eventDTO){
        return eventFacade.createEvent(eventDTO);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String update(@PathVariable long id,@RequestBody EventDTO eventDTO){
        return "update " + id;
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String delete(@PathVariable long id){
        return "delete " + id;
    }
}
