package experis.academy.filmapi.serviceImpl;

import experis.academy.filmapi.repository.CharacterRepository;
import experis.academy.filmapi.repository.MovieRepository;
import experis.academy.filmapi.service.MovieService;
import experis.academy.filmapi.exceptions.CharacterNotFoundException;
import experis.academy.filmapi.exceptions.MovieNotFoundException;
import experis.academy.filmapi.model.entites.Character;
import experis.academy.filmapi.model.entites.Movie;

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
        return movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
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
            throw new MovieNotFoundException(movie.getId());
        }

        return movieRepository.save(movie);
    }

    @Override
    public void deleteById(Integer id) {
        if (!movieRepository.existsById(id)) {
            throw new MovieNotFoundException(id);
        }

        movieRepository.deleteById(id);
    }

    @Override
    public Set<Character> findAllCharactersByMovie(int movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException(movieId));
        return movie.getCharacters();
    }

    @Override
    public Movie updateCharacters(int movieId, Set<Integer> charactersId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException(movieId));
        Set<Character> characters = new HashSet<>();

        for (int id : charactersId) {
            if (characterRepository.existsById(id)) {
                Character character = characterRepository.findById(id)
                        .orElseThrow(() -> new CharacterNotFoundException(id));
                characters.add(character);
            }
        }
        movie.setCharacters(characters);
        return movieRepository.save(movie);
    }
}
