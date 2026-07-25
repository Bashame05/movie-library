package com.makar.movielibrary.service;


import com.makar.movielibrary.model.Movie;
import com.makar.movielibrary.repository.MovieRepository;
import com.makar.movielibrary.specification.MovieSpecification;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies(Pageable pageable,String director , String genre , Integer year) {

        Specification<Movie> spec = Specification.allOf();

        if(director!=null){
            spec = spec.and(MovieSpecification.hasDirector(director));
        }
        if(genre!=null){
            spec = spec.and(MovieSpecification.hasGenre(genre));
        }
        if(year!=null){
            spec = spec.and(MovieSpecification.hasYear(year));
        }

        return movieRepository.findAll(spec,pageable).getContent();

    }

}
