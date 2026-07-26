package com.makar.movielibrary.controller;


import com.makar.movielibrary.model.User;
import com.makar.movielibrary.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {

        return new ResponseEntity<>(userService.registerUser(user), HttpStatus.OK);

    }


    @PostMapping("/login")
    public String loginUser(@RequestBody User user) {

        return userService.userLogin(user);

    }


}
