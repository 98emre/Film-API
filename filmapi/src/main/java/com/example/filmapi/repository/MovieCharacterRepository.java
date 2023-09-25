package com.example.filmapi.repository;

import com.example.filmapi.model.entites.MovieCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MovieCharacterRepository extends JpaRepository<MovieCharacter, Integer> {

}
