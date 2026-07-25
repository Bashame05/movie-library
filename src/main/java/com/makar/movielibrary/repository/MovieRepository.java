package com.makar.movielibrary.repository;

import com.makar.movielibrary.dto.MovieTitleCountResponseEntity;
import com.makar.movielibrary.model.Movie;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Year;
import java.util.List;
import java.util.Map;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> , JpaSpecificationExecutor<Movie> {

    Page<Movie> findByMovieTitle(String movieTitle, Pageable pageable);

    @Query("SELECT new com.makar.movielibrary.dto.MovieTitleCountResponseEntity(m.movieDirector , count(m.movieTitle)) as total_movies from Movie m GROUP BY m.movieDirector")
    List<MovieTitleCountResponseEntity> findMovieCountByMovieDirector();

    @Query("SELECT distinct m.movieDirector from Movie m")
    List<String> findAllMovieDirector();

    List<Movie> findByMovieDirectorOrMovieGenre(String movieDirector, String movieGenre);


    List<Movie> findByMovieYearAfter(Year year);

    List<Movie> findByMovieGenreContainingIgnoreCase(String movieGenre);

    List<Movie> findByMovieYearBefore(Year year);

    //@Param is only needed when we use named parameters
    @Query("SELECT count(m.movieTitle) from Movie m where m.movieYear < :movieYear")
    Integer findMovieCountByMovieYearBefore(@Param("movieYear")Year year);

    List<Movie> findByMovieYearBetween(Year year, Year year2);

    List<Movie> OrderByMovieYearDesc();

    @NativeQuery("SELECT movie_title from movies where movie_director = ?1")
    List<String> findMovieTitleByMovieDirector(String movieDirector);

    @NativeQuery("SELECT * from movies where movie_director = ?1")
    List<Map<String,Object>> findMoviesByMovieDirector(String movieDirector);

    @Transactional
    @Modifying
    @NativeQuery("UPDATE movies set movie_year = ?1 where movie_title = ?2")
    int updateMovieYearByMovieName(Year year , String movieName);
}
