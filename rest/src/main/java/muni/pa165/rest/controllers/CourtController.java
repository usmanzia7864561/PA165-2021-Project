package muni.pa165.rest.controllers;

import muni.pa165.api.dto.CourtDTO;
import muni.pa165.api.facade.CourtFacade;
import muni.pa165.persistence.entity.Court;
import muni.pa165.rest.models.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/court")
public class CourtController {

    @Autowired
    CourtFacade courtFacade;

    @GetMapping(value = "/")
     Collection<CourtDTO> index(){
        return courtFacade.getAllCourtDTO();
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> fetchById(@PathVariable long id){
        try {
            return ResponseEntity.ok(courtFacade.findById(id));
        }catch (Exception e){
            return ResponseEntity.unprocessableEntity().body(new GenericResponse("No court found"));
        }
    }

    @PostMapping(value = "/add",produces = MediaType.APPLICATION_JSON_VALUE)
    public CourtDTO add(@RequestBody CourtDTO courtdto){
        return courtFacade.registerCourt(courtdto);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String update(@PathVariable long id,@RequestBody CourtDTO courtDTO){
        return "update " + id;
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse> delete(@PathVariable long id){
        Optional<Court> court = courtFacade.findById(id);
        if (court.isPresent()){
            courtFacade.remove(court.get());
            return ResponseEntity.ok(new GenericResponse("Court deleted successfully"));
        }
        return ResponseEntity.badRequest().body(new GenericResponse("Failed to delete the court"));
    }
}
