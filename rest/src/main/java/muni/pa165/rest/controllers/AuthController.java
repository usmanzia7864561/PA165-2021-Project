package muni.pa165.rest.controllers;

import muni.pa165.api.dto.UserAuthenticateDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @GetMapping(value = "/")
    public @ResponseBody String index(){
        return "index";
    }


    @GetMapping(value = "/login")
    public @ResponseBody String login(@RequestBody UserAuthenticateDTO userAuthenticateDTO){
        return "Not avail";
    }

    @GetMapping(value = "/register")
    public @ResponseBody String register(){
        return "hello world";
    }
}
