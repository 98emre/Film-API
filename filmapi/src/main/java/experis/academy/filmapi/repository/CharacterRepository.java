package experis.academy.filmapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import experis.academy.filmapi.entity.Character;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Integer> {

}
