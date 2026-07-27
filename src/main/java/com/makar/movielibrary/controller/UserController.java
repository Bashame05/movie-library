package com.makar.movielibrary.controller;


import com.makar.movielibrary.model.Movie;
import com.makar.movielibrary.model.User;
import com.makar.movielibrary.security.UserPrincipal;
import com.makar.movielibrary.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


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
    public ResponseEntity<String> loginUser(@RequestBody User user) {

        return ResponseEntity.ok(userService.userLogin(user));

    }

    @PostMapping("/favourite")
    public ResponseEntity<String> addFavouriteMovie(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                    @RequestBody int movieId){
        UUID userId  = userPrincipal.getUserId();
        return ResponseEntity.status(HttpStatus.OK).body(userService.addFavouriteMovie(userId,movieId));
    }

    @GetMapping("/favourite")
    public ResponseEntity<List<Movie>> getFavouriteMovies(@AuthenticationPrincipal UserPrincipal userPrincipal){

        UUID userId  = userPrincipal.getUserId();

        return ResponseEntity.ok(userService.getFavouriteMovies(userId));

    }


}
