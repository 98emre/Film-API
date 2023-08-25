package experis.academy.filmapi.controller;

import java.net.URI;
import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import experis.academy.filmapi.mapper.MovieCharacterMapper;
import experis.academy.filmapi.mapper.FranchiseMapper;
import experis.academy.filmapi.mapper.MovieMapper;
import experis.academy.filmapi.model.dtos.character.MovieCharacterPostDTO;
import experis.academy.filmapi.model.dtos.franchise.FranchiseDTO;
import experis.academy.filmapi.model.dtos.franchise.FranchisePostDTO;
import experis.academy.filmapi.model.dtos.franchise.FranchiseUpdateDTO;
import experis.academy.filmapi.model.dtos.movie.MoviePostDTO;
import experis.academy.filmapi.model.entites.Franchise;
import experis.academy.filmapi.model.entites.Movie;
import experis.academy.filmapi.service.FranchiseService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "api/franchises")
@Tag(name = "Franchise", description = "Endpoints interact with franchise")
public class FranchiseController {

    private final FranchiseService franchiseService;
    private final FranchiseMapper franchiseMapper;
    private final MovieMapper movieMapper;
    private final MovieCharacterMapper movieCharacterMapper;

    @Autowired
    public FranchiseController(FranchiseService franchiseService, FranchiseMapper franchiseMapper,
            MovieMapper movieMapper, MovieCharacterMapper movieCharacterMapper) {
        this.franchiseService = franchiseService;
        this.franchiseMapper = franchiseMapper;
        this.movieMapper = movieMapper;
        this.movieCharacterMapper = movieCharacterMapper;
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addFranchise(@RequestBody FranchisePostDTO franchisePostDTO) {
        Franchise franchise = franchiseService.add(franchiseMapper.franchisePostDtoToFranchise(franchisePostDTO));
        URI location = URI.create("franchise/" + franchise.getId());

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<Collection<FranchiseDTO>> getAll() {
        return ResponseEntity.ok(franchiseMapper.franchisesToFranchisesDto(franchiseService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FranchiseDTO> findById(@PathVariable int id) {
        return ResponseEntity.ok(franchiseMapper.franchiseToFranchiseDto(franchiseService.findById(id)));

    }

    @PutMapping("/{id}/add/movies")
    public ResponseEntity<Void> addMoviesToFranchise(@PathVariable Integer id, @RequestBody Set<Integer> movieIds) {
        franchiseService.addMovies(id, movieIds);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/remove/movies")
    public ResponseEntity<Void> removeMoviesToFranchise(@PathVariable Integer id, @RequestBody Set<Integer> movieIds) {
        franchiseService.removeMovies(id, movieIds);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/movies")
    public ResponseEntity<Collection<MoviePostDTO>> getFranchiseMovies(@PathVariable Integer id) {
        Set<Movie> movies = franchiseService.findAllMoviesByFranchise(id);
        if (movies == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movieMapper.moviesToMoviesPostDto(movies));

    }

    @GetMapping("/{id}/movies/characters")
    public ResponseEntity<Collection<MovieCharacterPostDTO>> getFranchiseMovieCharacters(@PathVariable Integer id) {
        return ResponseEntity.ok(
                movieCharacterMapper.charactersToCharactersPostDTO(
                        franchiseService.findAllCharactersByFranchise(id)));
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Void> updateFranchise(@PathVariable Integer id,
            @RequestBody FranchiseUpdateDTO franchiseUpdateDTO) {

        franchiseUpdateDTO.setId(id);
        franchiseService.update(franchiseMapper.franchiseUpdateDtoToFranchise(franchiseUpdateDTO));

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteFranchiseById(@PathVariable Integer id) {
        franchiseService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
