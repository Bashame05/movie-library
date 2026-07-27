package com.makar.movielibrary.service;


import com.makar.movielibrary.model.Movie;
import com.makar.movielibrary.model.User;
import com.makar.movielibrary.repository.MovieRepository;
import com.makar.movielibrary.repository.UserRepository;
import com.makar.movielibrary.security.JWTService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final MovieRepository movieRepository;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       JWTService jwtService,
                       MovieRepository movieRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.movieRepository = movieRepository;
    }

    public User registerUser(User user) {
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        return userRepository.save(user);
    }

    public String userLogin(User user) {

        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getUserPassword()));
            return jwtService.generateToken(user.getUserName());
        } catch (BadCredentialsException e) {
            return e.getMessage();
        }
    }

    public String addFavouriteMovie(UUID userId , int movieId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new UsernameNotFoundException("Movie not found"));
        user.getFavouriteMovies().add(movie);
        userRepository.save(user);
        return "Movie added to favourites successfully";
    }
    public List<Movie> getFavouriteMovies(UUID userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user.getFavouriteMovies().stream().toList();
    }
}
