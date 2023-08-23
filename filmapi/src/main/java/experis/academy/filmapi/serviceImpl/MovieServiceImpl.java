package experis.academy.filmapi.serviceImpl;

import experis.academy.filmapi.model.Movie;
import experis.academy.filmapi.model.dto.movie.MovieDTO;
import experis.academy.filmapi.mapper.MovieMapper;
import experis.academy.filmapi.repository.MovieRepository;
import experis.academy.filmapi.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie findById(Integer id) {
        return movieRepository.findById(id).orElse(null);
    }

    @Override
    public Collection<Movie> findAll() {
        return movieRepository.findAll().stream()
                .sorted(Comparator.comparing(Movie::getId))
                .toList();
    }

    @Override
    public Movie add(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie update(Movie movie) {
        if (!movieRepository.existsById(movie.getId())) {
            return null;
        }

        return movieRepository.save(movie);
    }

    @Override
    public void deleteById(Integer id) {
        movieRepository.deleteById(id);
    }

}
