package experis.academy.filmapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import experis.academy.filmapi.model.MovieCharacter;

@Repository
public interface MovieCharacterRepository extends JpaRepository<MovieCharacter, Integer> {

}
