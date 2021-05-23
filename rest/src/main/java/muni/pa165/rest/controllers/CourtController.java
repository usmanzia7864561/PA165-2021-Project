package muni.pa165.rest.controllers;

import muni.pa165.api.dto.CourtDTO;
import muni.pa165.api.facade.CourtFacade;
import muni.pa165.persistence.entity.Court;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/court")
public class CourtController {

    @Autowired
    CourtFacade courtFacade;

    @GetMapping(value = "/")
     Collection<CourtDTO> index(){
        return courtFacade.getAllCourtDTO();
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public <Optional>Court fetchById(@PathVariable long id){
        return courtFacade.findById(id).orElseThrow();
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
    public String delete(@PathVariable long id){
        return "delete " + id;
    }
}
