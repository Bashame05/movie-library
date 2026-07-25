package com.makar.movielibrary.specification;

import com.makar.movielibrary.model.Movie;
import org.springframework.data.jpa.domain.Specification;

public class MovieSpecification {
    public static Specification<Movie>  hasDirector(String director){
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("movieDirector"),director);
    }

    public static Specification<Movie> hasGenre(String genre){
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("movieGenre"),genre);
    }
    public static Specification<Movie> hasYear(Integer year){
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("movieYear"),year);
    }
}
