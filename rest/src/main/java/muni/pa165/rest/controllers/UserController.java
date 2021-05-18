package muni.pa165.rest.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    @GetMapping(value = "/",produces = MediaType.TEXT_PLAIN_VALUE)
    public String fetch(){
        return "fetch";
    }

    @GetMapping(value = "/{id}",produces = MediaType.TEXT_PLAIN_VALUE)
    public String fetchById(@PathVariable long id){
        return "fetch " + id;
    }


    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public String create(){
        return "create";

    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String update(@PathVariable long id){
        return "update " + id;
    }


    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String delete(@PathVariable long id){
        return "delete " + id;
    }
}
