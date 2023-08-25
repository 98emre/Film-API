package experis.academy.filmapi.serviceImpl;

import java.util.Collection;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import experis.academy.filmapi.model.entites.Movie;
import experis.academy.filmapi.model.entites.MovieCharacter;
import experis.academy.filmapi.repository.MovieCharacterRepository;
import experis.academy.filmapi.repository.MovieRepository;
import experis.academy.filmapi.service.MovieCharacterService;
import experis.academy.filmapi.utilites.enums.Gender;
import experis.academy.filmapi.utilites.exceptions.MovieCharacterNotFoundException;

@Service
public class MovieCharacterServiceImpl implements MovieCharacterService {

    private final MovieCharacterRepository characterRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public MovieCharacterServiceImpl(MovieCharacterRepository characterRepository, MovieRepository movieRepository) {
        this.characterRepository = characterRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public MovieCharacter findById(Integer id) {
        return characterRepository.findById(id).orElseThrow(() -> new MovieCharacterNotFoundException(id));
    }

    @Override
    public Collection<MovieCharacter> findAll() {
        return characterRepository.findAll().stream()
                .sorted(Comparator.comparingInt(MovieCharacter::getId))
                .collect(Collectors.toList());
    }

    @Override
    public MovieCharacter add(MovieCharacter character) {
        return characterRepository.save(character);
    }

    @Override
    public MovieCharacter update(MovieCharacter character) {
        MovieCharacter updatedMovieCharacter = characterRepository.findById(character.getId())
                .orElseThrow(() -> new MovieCharacterNotFoundException(character.getId()));

        if (character.getName() != null) {
            updatedMovieCharacter.setName(character.getName());
        }

        if (character.getAlias() != null) {
            updatedMovieCharacter.setAlias(character.getAlias());
        }

        if (character.getGender() != null) {
            updatedMovieCharacter.setGender(Gender.valueOf(character.getGender().name().toUpperCase()));
        }

        if (character.getPictureURL() != null) {
            updatedMovieCharacter.setPictureURL(character.getPictureURL());
        }

        return characterRepository.save(updatedMovieCharacter);
    }

    @Override
    public void deleteById(Integer id) {
        MovieCharacter movieCharacter = characterRepository.findById(id)
                .orElseThrow(() -> new MovieCharacterNotFoundException(id));

        Set<Movie> movies = movieCharacter.getMovies();

        for (Movie movie : movies) {
            Set<MovieCharacter> characters = movie.getCharacters();
            characters.remove(movieCharacter);
            movie.setCharacters(characters);
            movieRepository.save(movie);
        }
        characterRepository.deleteById(id);
    }

}
