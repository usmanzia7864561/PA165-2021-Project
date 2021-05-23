package muni.pa165.rest.controllers;

import muni.pa165.api.dto.UserDTO;
import muni.pa165.api.facade.UserFacade;
import muni.pa165.rest.config.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Collection;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    UserFacade userFacade;

    @GetMapping(value = "/",produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<UserDTO> fetch(){
        return userFacade.getAllUsers();
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO fetchById(@PathVariable long id){
        return userFacade.findUserById(id);
    }

    @RolesAllowed({Roles.MANAGER})
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable long id){
        if (userFacade.delete(id)){
            return ResponseEntity.ok("User delete successfully");
        }
        return ResponseEntity.unprocessableEntity().body("Failed to delete given user");
    }
}
