package experis.academy.filmapi.service;

import java.util.Set;

import experis.academy.filmapi.model.Character;
import experis.academy.filmapi.model.Franchise;
import experis.academy.filmapi.model.Movie;

public interface FranchiseService extends CrudService<Franchise, Integer> {

    public Set<Movie> findAllMoviesByFranchise(int franchiseId);

    public Franchise updateMovies(int franchiseId, Set<Integer> moviesId);

    public Set<Character> findAllCharactersByFranchise(int franchiseId);

}
