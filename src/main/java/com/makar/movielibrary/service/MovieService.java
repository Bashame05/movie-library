package com.makar.movielibrary.service;


import com.makar.movielibrary.model.Movie;
import com.makar.movielibrary.repository.MovieRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies(Pageable pageable,String filter) {
        if (filter == null) {
            return movieRepository.findAll(pageable).getContent();
        }else{
            return movieRepository.findByMovieTitle(filter,pageable).getContent();
        }

    }

}
