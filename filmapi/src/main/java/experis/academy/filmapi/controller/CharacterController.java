package experis.academy.filmapi.controller;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import experis.academy.filmapi.mapper.CharacterMapper;
import experis.academy.filmapi.model.Character;
import experis.academy.filmapi.model.dto.character.CharacterDTO;
import experis.academy.filmapi.model.dto.character.CharacterPostDTO;
import experis.academy.filmapi.model.dto.character.CharacterUpdateDTO;
import experis.academy.filmapi.service.CharacterService;

@RestController
@RequestMapping(path = "api/characters")
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
