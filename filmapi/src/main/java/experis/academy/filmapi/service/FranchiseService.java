package experis.academy.filmapi.service;

import java.util.Set;

import experis.academy.filmapi.model.entites.MovieCharacter;
import experis.academy.filmapi.model.entites.Franchise;
import experis.academy.filmapi.model.entites.Movie;

public interface FranchiseService extends CrudService<Franchise, Integer> {

    public Set<Movie> findAllMoviesByFranchise(int franchiseId);

    public Franchise updateMovies(int franchiseId, Set<Integer> moviesId);

    public Set<MovieCharacter> findAllCharactersByFranchise(int franchiseId);

}
