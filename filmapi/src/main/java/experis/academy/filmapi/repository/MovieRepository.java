package experis.academy.filmapi.repository;

import org.springframework.stereotype.Repository;

import experis.academy.filmapi.model.Character;
import experis.academy.filmapi.model.Movie;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    @Query("SELECT * FROM character AS c " + 
            "INNER JOIN movie_character AS mc ON c.character_id = mc.character_id " +
            "INNER JOIN movie AS m ON mc.movie_id = m.movie_id " + 
            "WHERE m.movie_id = 1?")
    Set<Character> findCharactersByMovie(int movieId);

}
