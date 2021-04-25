package com.project.wchallenge.controllers;

import com.project.wchallenge.models.User;
import com.project.wchallenge.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUsersTemplate")
    private ResponseEntity<List<User>> getUsersTemplate() {
        return ResponseEntity.ok(userService.getUsersTemplate());
    }

}
