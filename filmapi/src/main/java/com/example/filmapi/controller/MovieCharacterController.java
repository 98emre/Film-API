package com.example.filmapi.controller;

import java.net.URI;
import java.util.Collection;

import com.example.filmapi.mapper.MovieCharacterMapper;
import com.example.filmapi.model.dtos.character.*;
import com.example.filmapi.model.entites.MovieCharacter;
import com.example.filmapi.service.MovieCharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "api/characters")
@Tag(name = "Characters", description = "Endpoints interact with characters")
public class MovieCharacterController {

    private final MovieCharacterService movieCharacterService;
    private final MovieCharacterMapper movieCharacterMapper;

    @Autowired
    public MovieCharacterController(MovieCharacterService movieCharacterService,
            MovieCharacterMapper movieCharacterMapper) {
        this.movieCharacterService = movieCharacterService;
        this.movieCharacterMapper = movieCharacterMapper;
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addCharacter(@RequestBody MovieCharacterPostDTO characterPostDTO) {
        MovieCharacter character = movieCharacterService
                .add(movieCharacterMapper.characterPostToCharacter(characterPostDTO));
        URI location = URI.create("characters/" + character.getId());

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<Collection<MovieCharacterDTO>> getAll() {
        return ResponseEntity.ok(movieCharacterMapper.charactersToCharactersDTO(movieCharacterService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieCharacterDTO> getCharacter(@PathVariable Integer id) {
        return ResponseEntity.ok(movieCharacterMapper.characterToCharacterDTO(movieCharacterService.findById(id)));
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Void> updateCharacter(@RequestBody MovieCharacterUpdateDTO movieCharacterUpdateDTO,
            @PathVariable int id) {

        movieCharacterUpdateDTO.setId(id);
        movieCharacterService.update(movieCharacterMapper.characterUpdateToCharacter(movieCharacterUpdateDTO));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteCharacterById(@PathVariable Integer id) {
        movieCharacterService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
