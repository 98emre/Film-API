package experis.academy.filmapi.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import experis.academy.filmapi.model.dto.CharacterDto;
import experis.academy.filmapi.service.CharacterService;

@RestController
@RequestMapping(path = "api/characters")
public class CharacterController {

    private final CharacterService characterService;

    @Autowired
    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping
    public ResponseEntity<List<CharacterDto>> getAll() {
        try {
            List<CharacterDto> characters = characterService.findAll().stream().collect(Collectors.toList());
            return ResponseEntity.ok(characters);
        }

        catch (Exception e) {
            System.out.println("Error: " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterDto> getCharacter(@PathVariable Integer id) {
        try {
            CharacterDto character = characterService.findById(id);
            if (character == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(character);
        }

        catch (Exception e) {
            System.out.println("Error " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<CharacterDto> addCharacter(@RequestBody CharacterDto characterDto) {
        return ResponseEntity.ok(characterService.add(characterDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CharacterDto> updateCharacter(@PathVariable Integer id,
            @RequestBody CharacterDto characterDto) {

        if (characterService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }

        characterDto.setId(id);
        return ResponseEntity.ok(characterService.update(characterDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable Integer id) {
        characterService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
