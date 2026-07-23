package com.makar.movielibrary.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Year;


@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name= "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieId;
    private String movieTitle;
    private Year movieYear;
    private String movieGenre;
    private String movieDirector;


}
