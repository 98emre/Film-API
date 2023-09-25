package com.example.filmapi.service;

import com.example.filmapi.model.entites.*;

import java.util.Set;

public interface FranchiseService extends CrudService<Franchise, Integer> {

    public Set<Movie> findAllMoviesByFranchise(int franchiseId);

    public Franchise addMovies(int franchiseId, Set<Integer> movieIds);

    public Franchise removeMovies(int franchiseId, Set<Integer> movieIds);

    public Set<MovieCharacter> findAllCharactersByFranchise(int franchiseId);

}
