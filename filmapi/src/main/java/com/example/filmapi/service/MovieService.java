package com.example.filmapi.service;

import com.example.filmapi.model.entites.Movie;
import com.example.filmapi.model.entites.MovieCharacter;

import java.util.Set;


public interface MovieService extends CrudService<Movie, Integer> {

    public Set<MovieCharacter> findAllCharactersByMovie(int movieId);

    public Movie addCharacters(int movieId, Set<Integer> charactersId);

    public Movie removeCharacters(int movieId, Set<Integer> charactersId);

}
