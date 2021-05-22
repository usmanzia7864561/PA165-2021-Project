package muni.pa165.rest.controllers;

import muni.pa165.api.dto.UserDTO;
import muni.pa165.api.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String update(@PathVariable long id,@RequestBody UserDTO userDTO){
        return "update " + id;
    }


    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String delete(@PathVariable long id){
        return "delete " + id;
    }
}
