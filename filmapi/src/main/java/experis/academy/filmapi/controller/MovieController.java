package experis.academy.filmapi.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import experis.academy.filmapi.model.Movie;
import experis.academy.filmapi.service.MovieService;

@RestController
@RequestMapping(path = "api/movie")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<Collection<Movie>> getAll() {
        try {
            return ResponseEntity.ok(movieService.findAll());
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(movieService.findById(id));
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error " + e);
            return null;
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Character> addCharacter(@RequestBody Character character) {
        return ResponseEntity.ok(characterService.add(character));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Character> updateCharacter(@PathVariable Integer id, @RequestBody Character character) {
        character.setId(id);
        return ResponseEntity.ok(characterService.update(character));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCharacter(@PathVariable Integer id) {
        characterService.deleteById(id);
    }

}
