package experis.academy.filmapi.service;

import java.util.Set;

import experis.academy.filmapi.model.Movie;
import experis.academy.filmapi.model.Character;

public interface MovieService extends CrudService<Movie, Integer> {

    
    public Set<Character> findCharactersByMovie(int movieId);

}
