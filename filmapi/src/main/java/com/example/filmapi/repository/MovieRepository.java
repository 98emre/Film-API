package com.example.filmapi.repository;

import com.example.filmapi.model.entites.Movie;
import com.example.filmapi.model.entites.MovieCharacter;
import org.springframework.stereotype.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    @Query(value = "SELECT c.character_id, c.alias, c.character_name, c.picture_url, c.gender FROM character AS c " +
            "INNER JOIN movie_character AS mc ON c.character_id = mc.character_id " +
            "INNER JOIN movie AS m ON mc.movie_id = m.movie_id " +
            "WHERE m.movie_id = ?1", nativeQuery = true)
    List<MovieCharacter> findCharactersByMovie(int movieId);

}
