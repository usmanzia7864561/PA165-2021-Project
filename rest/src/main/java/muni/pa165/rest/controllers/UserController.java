package muni.pa165.rest.controllers;

import muni.pa165.api.dto.UserResponseDTO;
import muni.pa165.api.dto.UserUpdateDTO;
import muni.pa165.api.facade.UserFacade;
import muni.pa165.rest.config.Roles;
import muni.pa165.rest.models.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Collection;

@RestController
@CrossOrigin
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    UserFacade userFacade;

    @GetMapping(value = "/",produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<UserResponseDTO> fetch(){
        return userFacade.getAllUsers();
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponseDTO fetchById(@PathVariable long id){
        return userFacade.findUserById(id);
    }

    @PutMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public UserUpdateDTO update(@PathVariable long id, @RequestBody UserUpdateDTO userDTO){
        return userFacade.update(id, userDTO);
    }

    @RolesAllowed(Roles.MANAGER)
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse> delete(@PathVariable long id){
        if (userFacade.delete(id)){
            return ResponseEntity.ok(new GenericResponse("User delete successfully"));
        }
        return ResponseEntity.unprocessableEntity().body(new GenericResponse("Failed to delete given user"));
    }
}
