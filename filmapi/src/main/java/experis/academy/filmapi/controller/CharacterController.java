package experis.academy.filmapi.controller;

import java.net.URI;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import experis.academy.filmapi.mapper.CharacterMapper;
import experis.academy.filmapi.model.Character;
import experis.academy.filmapi.model.dto.character.CharacterDTO;
import experis.academy.filmapi.model.dto.character.CharacterPostDTO;
import experis.academy.filmapi.model.dto.character.CharacterUpdateDTO;
import experis.academy.filmapi.service.CharacterService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "api/characters")
@Tag(name = "Characters", description = "Endpoints interact with characters")
public class CharacterController {

    private final CharacterService characterService;
    private final CharacterMapper characterMapper;

    @Autowired
    public CharacterController(CharacterService characterService, CharacterMapper characterMapper) {
        this.characterService = characterService;
        this.characterMapper = characterMapper;
    }

    @GetMapping
    public ResponseEntity<Collection<CharacterDTO>> getAll() {
        return ResponseEntity.ok(characterMapper.charactersToCharactersDTO(characterService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterDTO> getCharacter(@PathVariable Integer id) {
        return ResponseEntity.ok(characterMapper.characterToCharacterDTO(characterService.findById(id)));
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addCharacter(@RequestBody CharacterPostDTO characterPostDTO) {
        Character character = characterService.add(characterMapper.characterPostToCharacter(characterPostDTO));
        URI location = URI.create("characters/" + character.getId());

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Void> updateCharacter(@RequestBody CharacterUpdateDTO characterUpdateDTO,
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
