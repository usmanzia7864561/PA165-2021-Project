package muni.pa165.rest.controllers;

import muni.pa165.api.facade.UserFacade;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;

@Controller
@RequestMapping(path = "/auth")
public class AuthController {
    @Inject
    UserFacade userFacade;

    @GetMapping(value = "/login", produces = MediaType.TEXT_PLAIN_VALUE)
    public String index(){
        return "hello world";
    }

    @GetMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public String login(){
        return "hello world";
    }
}
