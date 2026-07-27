package com.makar.movielibrary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name= "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;
    private String userName;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String userPassword;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "favourites",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "movieId")
    )
    @JsonIgnore
    //dont add json ignore its for testing purposes for the response better and correct way is creating a dto
    private Set<Movie> favouriteMovies = new HashSet<>();
}
