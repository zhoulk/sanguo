package com.mud.controller;

import org.springframework.web.bind.annotation.*;

/**
 * Created by leeesven on 17/8/19.
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @PostMapping(value = "/register", consumes = "application/json")
    public String api_user_register(@RequestParam String name, @RequestParam String password){
        return "Welcome, " + name;
    }
}
