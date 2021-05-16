package muni.pa165.rest.controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/auth")
public class UserController {

    @GetMapping(value = "/",produces = MediaType.TEXT_PLAIN_VALUE)
    public String index(){
        return "hello world";
    }

    @GetMapping(value = "/login",produces = MediaType.TEXT_PLAIN_VALUE)
    public String login(){
        return "hello world";
    }
}
