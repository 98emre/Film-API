package experis.academy.filmapi.service;

import java.util.Set;

import experis.academy.filmapi.model.entites.Character;
import experis.academy.filmapi.model.entites.Movie;

public interface MovieService extends CrudService<Movie, Integer> {

    public Set<Character> findAllCharactersByMovie(int movieId);

    public Movie updateCharacters(int movieId, Set<Integer> charactersId);
}
