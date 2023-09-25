package com.example.filmapi.controller;

import java.net.URI;
import java.util.Collection;
import java.util.Set;

import com.example.filmapi.mapper.MovieCharacterMapper;
import com.example.filmapi.mapper.MovieMapper;
import com.example.filmapi.model.dtos.character.*;
import com.example.filmapi.model.dtos.movie.*;
import com.example.filmapi.model.entites.Movie;
import com.example.filmapi.model.entites.MovieCharacter;
import com.example.filmapi.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping
    public ResponseEntity<Collection<MovieDTO>> getAll() {
        return ResponseEntity.ok(movieMapper.moviesToMoviesDto(movieService.findAll()));
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addMovie(@RequestBody MoviePostDTO moviePostDTO) {
        Movie movie = movieService.add(movieMapper.moviePostDtoToMovie(moviePostDTO));

        URI location = URI.create("movie/" + movie.getId());

        return ResponseEntity.created(location).build();
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

    @PutMapping("/{id}/add/characters")
    public ResponseEntity<Void> addMovieCharacters(@PathVariable Integer id,
            @RequestBody Set<Integer> charactersId) {
        movieService.addCharacters(id, charactersId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/remove/characters")
    public ResponseEntity<Void> removeMovieCharacters(@PathVariable Integer id,
            @RequestBody Set<Integer> charactersId) {
        movieService.removeCharacters(id, charactersId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteMovie(@PathVariable Integer id) {
        movieService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
