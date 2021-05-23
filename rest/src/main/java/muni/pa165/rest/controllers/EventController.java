package muni.pa165.rest.controllers;

import muni.pa165.api.dto.EventDTO;
import muni.pa165.api.facade.EventFacade;
import muni.pa165.rest.models.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin
@RequestMapping("/event")
public class EventController {
    @Autowired
    EventFacade eventFacade;

    @GetMapping(value = "/",produces = MediaType.APPLICATION_JSON_VALUE)
    Collection<EventDTO> index(){
        return eventFacade.getAllEvents();
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public  EventDTO fetchById(@PathVariable long id){
        return eventFacade.findEventById(id);
    }

    @GetMapping(value = "/court/{courtId}",produces = MediaType.APPLICATION_JSON_VALUE)
    Collection<EventDTO> byCourt(@PathVariable long courtId){
        return eventFacade.getAllCourtEvents(courtId);
    }

    @PostMapping(value = "/{courtId}/add",produces = MediaType.APPLICATION_JSON_VALUE)
    public EventDTO add(@PathVariable long courtId, @RequestBody EventDTO eventDTO){
        return eventFacade.createEvent(courtId,eventDTO);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String update(@PathVariable long id,@RequestBody EventDTO eventDTO){
        return "update " + id;
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse> delete(@PathVariable long id){
        if (eventFacade.remove(id)){
            return ResponseEntity.ok().body(new GenericResponse("Event deleted successfully"));
        }
        return ResponseEntity.status(404).body(new GenericResponse("Event not found"));
    }
}
