package experis.academy.filmapi.service;

import java.util.Set;

import experis.academy.filmapi.model.Movie;
import experis.academy.filmapi.model.Character;

public interface MovieService extends CrudService<Movie, Integer> {

    
    public Set<Character> findAllCharactersByMovie(int movieId);

    public Movie updateCharacters(int movieId, Set<Integer> charactersId);
}
