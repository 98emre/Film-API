package experis.academy.filmapi.repository;

import org.springframework.stereotype.Repository;

import experis.academy.filmapi.model.Movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    @Modifying
    @Query("UPDATE movie m SET m.franchise_id = 2? WHERE m.movie_id = 1?")
    void updateFranchiseById(int movieId, int franchiseId);

    @Modifying
    @Query("UPDATE movie_character mc SET mc.character_id = 3? WHERE movie_id = 1? AND character_id = 2?")
    void updateCharacterById(int movieId, int characterId, int newCharacterId);

}
