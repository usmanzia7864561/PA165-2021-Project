package muni.pa165.rest.controllers;

import muni.pa165.api.dto.UserAuthenticateDTO;
import muni.pa165.api.dto.UserDTO;
import muni.pa165.api.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    UserFacade userFacade;

    @PostMapping(value = "/login")
    public boolean login(UserAuthenticateDTO userAuthenticateDTO){
        return userFacade.authenticate(userAuthenticateDTO);
    }

    @PostMapping(value = "/register",produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO register(@RequestBody UserDTO userDTO){
        System.out.println(userDTO.toString());
        return userFacade.registerUser(userDTO);
    }
}
