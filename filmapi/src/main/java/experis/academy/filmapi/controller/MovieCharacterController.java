package experis.academy.filmapi.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import experis.academy.filmapi.model.MovieCharacter;
import experis.academy.filmapi.service.MovieCharacterService;

@RestController
@RequestMapping(path = "api/characters")
public class MovieCharacterController {

    private final MovieCharacterService characterService;

    @Autowired
    public MovieCharacterController(MovieCharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping
    public ResponseEntity<List<MovieCharacter>> getAll() {
        try {
            return ResponseEntity.ok((List<MovieCharacter>) characterService.findAll());
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieCharacter> getCharacter(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(characterService.findById(id));
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error " + e);
            return null;
        }
    }

    @PostMapping("/add")
    public ResponseEntity<MovieCharacter> addCharacter(@RequestBody MovieCharacter character) {
        return ResponseEntity.ok(characterService.add(character));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<MovieCharacter> updateCharacter(@PathVariable Integer id,
            @RequestBody MovieCharacter character) {
        if (characterService.findById(id) == null) {
            return null;
        }

        MovieCharacter updatedCharacter = characterService.findById(id);
        updatedCharacter.setName(character.getName());
        updatedCharacter.setAlias(character.getAlias());
        updatedCharacter.setGender(character.getGender());
        updatedCharacter.setPictureURL(character.getPictureURL());

        return ResponseEntity.ok(characterService.update(updatedCharacter));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCharacter(@PathVariable Integer id) {
        characterService.deleteById(id);
    }

}
