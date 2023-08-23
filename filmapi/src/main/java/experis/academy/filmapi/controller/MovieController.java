package experis.academy.filmapi.controller;

import java.net.URI;
import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import experis.academy.filmapi.mapper.CharacterMapper;
import experis.academy.filmapi.mapper.MovieMapper;
import experis.academy.filmapi.model.Character;
import experis.academy.filmapi.model.Movie;
import experis.academy.filmapi.model.dto.character.CharacterDTO;
import experis.academy.filmapi.model.dto.character.CharacterPostDTO;
import experis.academy.filmapi.model.dto.movie.MovieDTO;
import experis.academy.filmapi.model.dto.movie.MoviePostDTO;
import experis.academy.filmapi.model.dto.movie.MovieUpdateDTO;
import experis.academy.filmapi.service.MovieService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping(path = "api/movie")
public class MovieController {

    private final MovieService movieService;
    private final MovieMapper movieMapper;
    private final CharacterMapper characterMapper;

    @Autowired
    public MovieController(MovieService movieService, MovieMapper movieMapper, CharacterMapper characterMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
        this.characterMapper = characterMapper;
    }

    @GetMapping
    public ResponseEntity<Collection<MovieDTO>> getAll() {
        return ResponseEntity.ok(movieMapper.moviesToMoviesDto(movieService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMovie(@PathVariable Integer id) {
        return ResponseEntity.ok(movieMapper.movieToMovieDto(movieService.findById(id)));
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addMovie(@RequestBody MoviePostDTO moviePostDTO) {
        Movie movie = movieService.add(movieMapper.moviePostDtoToMovie(moviePostDTO));

        URI location = URI.create("movie/" + movie.getId());

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateMovie(@PathVariable Integer id, @RequestBody MovieUpdateDTO movieUpdateDTO) {

        movieUpdateDTO.setId(id);
        movieService.update(movieMapper.movieUpdateDtoToMovie(movieUpdateDTO));

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Integer id) {
        movieService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/characters/{id}")
    public ResponseEntity<Void> updateMovieCharacters(@PathVariable Integer id, @RequestBody Set<Integer> charactersId) {
        movieService.updateCharacters(id, charactersId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/characters/{id}")
    public ResponseEntity<Collection<CharacterPostDTO>> getMovieCharacters(@PathVariable Integer id) {
        Set<Character> characters = movieService.findCharactersByMovie(id);
        if (characters == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(characterMapper.charactersToCharactersPostDTO(characters));
        
    }
}
