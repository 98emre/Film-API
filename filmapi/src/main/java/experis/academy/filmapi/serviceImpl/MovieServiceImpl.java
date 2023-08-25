package experis.academy.filmapi.serviceImpl;

import experis.academy.filmapi.repository.MovieCharacterRepository;
import experis.academy.filmapi.repository.MovieRepository;
import experis.academy.filmapi.service.MovieService;
import experis.academy.filmapi.utilites.exceptions.MovieCharacterNotFoundException;
import experis.academy.filmapi.utilites.exceptions.MovieNotFoundException;
import experis.academy.filmapi.model.entites.MovieCharacter;
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
    private final MovieCharacterRepository characterRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, MovieCharacterRepository characterRepository) {
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
    public Set<MovieCharacter> findAllCharactersByMovie(int movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException(movieId));
        return movie.getCharacters();
    }

    @Override
    public Movie updateCharacters(int movieId, Set<Integer> charactersId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException(movieId));
        Set<MovieCharacter> characters = new HashSet<>();

        for (int id : charactersId) {
            if (characterRepository.existsById(id)) {
                MovieCharacter character = characterRepository.findById(id)
                        .orElseThrow(() -> new MovieCharacterNotFoundException(id));
                characters.add(character);
            }
        }
        movie.setCharacters(characters);
        return movieRepository.save(movie);
    }
}
