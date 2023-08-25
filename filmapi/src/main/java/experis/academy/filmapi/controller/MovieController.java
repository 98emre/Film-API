package experis.academy.filmapi.controller;

import java.net.URI;
import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import experis.academy.filmapi.mapper.MovieCharacterMapper;
import experis.academy.filmapi.mapper.MovieMapper;
import experis.academy.filmapi.model.dtos.character.MovieCharacterPostDTO;
import experis.academy.filmapi.model.dtos.movie.MovieDTO;
import experis.academy.filmapi.model.dtos.movie.MoviePostDTO;
import experis.academy.filmapi.model.dtos.movie.MovieUpdateDTO;
import experis.academy.filmapi.model.entites.MovieCharacter;
import experis.academy.filmapi.model.entites.Movie;
import experis.academy.filmapi.service.MovieService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "api/movies")
@Tag(name = "Movie", description = "Endpoints interact with movie")
public class MovieController {

    private final MovieService movieService;
    private final MovieMapper movieMapper;
    private final MovieCharacterMapper characterMapper;

    @Autowired
    public MovieController(MovieService movieService, MovieMapper movieMapper, MovieCharacterMapper characterMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
        this.characterMapper = characterMapper;
    }
    
    @PostMapping("/add")
    public ResponseEntity<Void> addMovie(@RequestBody MoviePostDTO moviePostDTO) {
        Movie movie = movieService.add(movieMapper.moviePostDtoToMovie(moviePostDTO));

        URI location = URI.create("movie/" + movie.getId());

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<Collection<MovieDTO>> getAll() {
        return ResponseEntity.ok(movieMapper.moviesToMoviesDto(movieService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMovie(@PathVariable Integer id) {
        return ResponseEntity.ok(movieMapper.movieToMovieDto(movieService.findById(id)));
    }

    @GetMapping("/{id}/characters")
    public ResponseEntity<Collection<MovieCharacterPostDTO>> getMovieCharacters(@PathVariable Integer id) {
        Set<MovieCharacter> characters = movieService.findAllCharactersByMovie(id);
        if (characters == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(characterMapper.charactersToCharactersPostDTO(characters));
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Void> updateMovie(@PathVariable Integer id, @RequestBody MovieUpdateDTO movieUpdateDTO) {

        movieUpdateDTO.setId(id);
        movieService.update(movieMapper.movieUpdateDtoToMovie(movieUpdateDTO));

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/update/characters")
    public ResponseEntity<Void> updateMovieCharacters(@PathVariable Integer id,
            @RequestBody Set<Integer> charactersId) {
        movieService.updateCharacters(id, charactersId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteMovie(@PathVariable Integer id) {
        movieService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
