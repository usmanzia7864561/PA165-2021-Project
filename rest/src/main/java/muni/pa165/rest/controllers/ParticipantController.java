package muni.pa165.rest.controllers;

import muni.pa165.api.dto.ParticipantDTO;
import muni.pa165.api.facade.EventFacade;
import muni.pa165.api.facade.ParticipantFacade;
import muni.pa165.rest.models.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin
@RequestMapping("/participant")
public class ParticipantController {
    @Autowired
    ParticipantFacade participantFacade;

    @Autowired
    EventFacade eventFacade;

    @GetMapping(value = "/event/{eventId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<ParticipantDTO> byEvent(@PathVariable long eventId){
        return participantFacade.getByEventParticipants(eventId);
    }

    @PostMapping(value = "/{eventId}/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ParticipantDTO add(@PathVariable long eventId,@RequestBody ParticipantDTO participantDTO){
        return eventFacade.addParticipant(eventId,participantDTO);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse> delete(@PathVariable long id){
        ParticipantDTO participantDTO = participantFacade.findById(id);
        if (participantDTO.getName() != null) {
            participantFacade.remove(participantDTO);
            return ResponseEntity.ok(new GenericResponse("Participant deleted successfully"));
        }
        return ResponseEntity.status(404).body(new GenericResponse("Participant not found"));
    }
}
