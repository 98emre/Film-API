package experis.academy.filmapi.controller;

import java.util.Collection;

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

import experis.academy.filmapi.mappers.MovieMapper;
import experis.academy.filmapi.model.Movie;
import experis.academy.filmapi.service.MovieService;

@RestController
@RequestMapping(path = "api/movie")
public class MovieController {

    private final MovieService movieService;
    private final MovieMapper movieMapper;

    @Autowired
    public MovieController(MovieService movieService, MovieMapper movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
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
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        return ResponseEntity.ok(movieService.add(movie));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Integer id, @RequestBody Movie movie) {
        if (movieService.findById(id) == null) {
            return null;
        }

        Movie updatedMovie = movieService.findById(id);
        updatedMovie.setTitle(movie.getTitle());
        updatedMovie.setGenre(movie.getGenre());
        updatedMovie.setDirector(movie.getDirector());
        updatedMovie.setPosterPictureURL(movie.getPosterPictureURL());
        updatedMovie.setReleaseYear(movie.getReleaseYear());
        updatedMovie.setTrailerLink(movie.getTrailerLink());

        return ResponseEntity.ok(movieService.update(updatedMovie));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMovie(@PathVariable Integer id) {
        movieService.deleteById(id);
    }

}
