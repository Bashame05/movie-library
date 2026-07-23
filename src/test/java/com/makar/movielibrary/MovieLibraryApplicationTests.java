package com.makar.movielibrary;

import com.makar.movielibrary.dto.MovieTitleCountResponseEntity;
import com.makar.movielibrary.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Year;
import java.util.List;

@SpringBootTest
class MovieLibraryApplicationTests {

    private final MovieRepository movieRepository;
    @Autowired
    public MovieLibraryApplicationTests(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    @Test
    void loadMoviesByName() {
        movieRepository.findAll()
                .forEach(System.out::println);
    }
    @Test
    void loadMovieCountOfDirectors() {
        List<MovieTitleCountResponseEntity> movieCountList =  movieRepository.findMovieCountByMovieDirector();
        for(MovieTitleCountResponseEntity movieCount:movieCountList){
            System.out.println(movieCount);
        }
    }
    @Test
    void loadAllDirectors() {
        movieRepository.findAllMovieDirector()
                .forEach(System.out::println);
    }
    @Test
    void loadByMovieDirectorOrMovieGenre() {
        movieRepository.findByMovieDirectorOrMovieGenre("Curry Barker","Crime/Mystery")
                .forEach(System.out::println);
    }

    @Test
    void loadMoviesAfterYear(){
        movieRepository.findByMovieYearAfter(Year.of(2000))
                .forEach(System.out::println);
    }

    @Test
    void loadBySpecificMovieGenre(){
        movieRepository.findByMovieGenreContainingIgnoreCase("drama")
                .forEach(System.out::println);
    }

    @Test
    void loadMoviesBeforeYear(){
        movieRepository.findByMovieYearBefore(Year.of(2000))
                .forEach(System.out::println);
    }

    @Test
    void movieCountBeforeYear(){
        System.out.println(movieRepository.findMovieCountByMovieYearBefore(Year.of(2000)));
    }

    @Test
    void loadMoviesBetweenYear(){
        movieRepository.findByMovieYearBetween(Year.of(2000),Year.of(2010))
                .forEach(System.out::println);
    }

    @Test
    void loadMovieByDescendingYear(){
        movieRepository.OrderByMovieYearDesc()
                .forEach(System.out::println);
    }

    @Test
    void loadMovieTitleByMovieDirector(){
        movieRepository.findMovieTitleByMovieDirector("David Fincher")
                .forEach(System.out::println);
    }

    @Test
    void loadMoviesByMovieDirector(){
        movieRepository.findMoviesByMovieDirector("Bong Joon-ho")
                .forEach(map ->
                map.forEach((key, value) ->
                        System.out.println(key + " " + value))
        );
    }

    @Test
    void updateMovieYearByMovieName(){
        System.out.println(movieRepository.updateMovieYearByMovieName(Year.of(2026),"Obsession"));
    }
}
