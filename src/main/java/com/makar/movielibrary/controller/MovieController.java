package com.makar.movielibrary.controller;

import com.makar.movielibrary.model.Movie;
import com.makar.movielibrary.service.MovieService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    //spring data jpa uses zero based indexing for pages
    //so when used professionally some use pageNo-1 for cleaner 1 based indexing but make necessary conversions
    //and handle the pageNo input properly
    public ResponseEntity<List<Movie>> findAllMovies(@RequestParam(required = false, defaultValue = "0") int pageNo,
                                                     @RequestParam(required = false, defaultValue = "5") int pageSize,
                                                     @RequestParam(required = false, defaultValue = "movieTitle") String sortBy,
                                                     @RequestParam(required = false, defaultValue = "ASC") String sortDir,
                                                     @RequestParam(required = false) String director,
                                                     @RequestParam(required = false) String genre,
                                                     @RequestParam(required = false) Integer year) {
        Sort sort = Sort.by(Sort.Direction.ASC, sortBy);
        if (sortDir.equals("DESC")) {
            sort = Sort.by(Sort.Direction.DESC, sortBy);
        }
        return ResponseEntity.ok(movieService.getAllMovies(PageRequest.of(pageNo, pageSize, sort), director,genre,year));
    }

}
