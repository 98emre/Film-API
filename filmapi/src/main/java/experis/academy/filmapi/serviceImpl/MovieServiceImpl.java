package experis.academy.filmapi.serviceImpl;


import experis.academy.filmapi.model.Movie;
import experis.academy.filmapi.repository.CharacterRepository;
import experis.academy.filmapi.repository.MovieRepository;
import experis.academy.filmapi.service.MovieService;
import experis.academy.filmapi.model.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final CharacterRepository characterRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, CharacterRepository characterRepository) {
        this.movieRepository = movieRepository;
        this.characterRepository = characterRepository;
    }

    @Override
    public Movie findById(Integer id) {
        return movieRepository.findById(id).orElse(null);
    }

    @Override
    public Collection<Movie> findAll() {
        return movieRepository.findAll().stream()
                .sorted(Comparator.comparing(Movie::getId))
                .toList();
    }

    @Override
    public Movie add(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie update(Movie movie) {
        if (!movieRepository.existsById(movie.getId())) {
            return null;
        }

        return movieRepository.save(movie);
    }

    @Override
    public void deleteById(Integer id) {
        movieRepository.deleteById(id);
    }

    @Override
    public Set<Character> findAllCharactersByMovie(int movieId) {
        if (!movieRepository.existsById(movieId)) {
            return null;
        }
        Movie movie = movieRepository.findById(movieId).orElse(null);
        return movie.getCharacters();
    }

    @Override
    public Movie updateCharacters(int movieId, Set<Integer> charactersId) {
        if (!movieRepository.existsById(movieId)) {
            return null;
        }
        
        Movie movie = movieRepository.findById(movieId).orElse(null);
            Set<Character> characters = new HashSet<>();

            for (int id: charactersId) {
                if (characterRepository.existsById(id)) {
                    Character character = characterRepository.findById(id).orElse(null);
                    characters.add(character);
                }
            }
            movie.setCharacters(characters);
        return movieRepository.save(movie);
    }
}
