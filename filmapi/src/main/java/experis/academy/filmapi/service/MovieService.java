package experis.academy.filmapi.service;

import java.util.Set;

import experis.academy.filmapi.model.entites.MovieCharacter;
import experis.academy.filmapi.model.entites.Movie;

public interface MovieService extends CrudService<Movie, Integer> {

    public Set<MovieCharacter> findAllCharactersByMovie(int movieId);

    public Movie updateCharacters(int movieId, Set<Integer> charactersId);
}
