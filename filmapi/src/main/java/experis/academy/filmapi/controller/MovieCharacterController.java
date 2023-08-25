package experis.academy.filmapi.controller;

import java.net.URI;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import experis.academy.filmapi.mapper.MovieCharacterMapper;
import experis.academy.filmapi.model.dtos.character.MovieCharacterDTO;
import experis.academy.filmapi.model.dtos.character.MovieCharacterPostDTO;
import experis.academy.filmapi.model.dtos.character.MovieCharacterUpdateDTO;
import experis.academy.filmapi.model.entites.MovieCharacter;
import experis.academy.filmapi.service.MovieCharacterService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "api/characters")
@Tag(name = "Characters", description = "Endpoints interact with characters")
public class MovieCharacterController {

    private final MovieCharacterService characterService;
    private final MovieCharacterMapper characterMapper;

    @Autowired
    public MovieCharacterController(MovieCharacterService characterService, MovieCharacterMapper characterMapper) {
        this.characterService = characterService;
        this.characterMapper = characterMapper;
    }

    @GetMapping
    public ResponseEntity<Collection<MovieCharacterDTO>> getAll() {
        return ResponseEntity.ok(characterMapper.charactersToCharactersDTO(characterService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieCharacterDTO> getCharacter(@PathVariable Integer id) {
        return ResponseEntity.ok(characterMapper.characterToCharacterDTO(characterService.findById(id)));
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addCharacter(@RequestBody MovieCharacterPostDTO characterPostDTO) {
        MovieCharacter character = characterService.add(characterMapper.characterPostToCharacter(characterPostDTO));
        URI location = URI.create("characters/" + character.getId());

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Void> updateCharacter(@RequestBody MovieCharacterUpdateDTO characterUpdateDTO,
            @PathVariable int id) {

        characterUpdateDTO.setId(id);
        characterService.update(characterMapper.characterUpdateToCharacter(characterUpdateDTO));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteCharacterById(@PathVariable Integer id) {
        characterService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
