package experis.academy.filmapi.serviceImpl;


import experis.academy.filmapi.model.Movie;
import experis.academy.filmapi.model.dto.MovieDto;
import experis.academy.filmapi.mapper.MovieMapper;
import experis.academy.filmapi.repository.MovieRepository;
import experis.academy.filmapi.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }

    @Override
    public MovieDto findById(Integer id) {
        return movieRepository.findById(id)
                .map(movieMapper::toMovieDto)
                .orElse(null);
    }

    @Override
    public Collection<MovieDto> findAll() {
        return movieRepository.findAll()
                .stream()
                .map(movieMapper::toMovieDto)
                .collect(Collectors.toList());
    }

    @Override
    public MovieDto add(MovieDto movieDto) {
        Movie savedMovie = movieRepository.save(movieMapper.toMovie(movieDto));
        return movieMapper.toMovieDto(savedMovie);
    }

    @Override
    public MovieDto update(MovieDto movieDto) {
        Movie updatedMovie = movieRepository.save(movieMapper.toMovie(movieDto));
        return movieMapper.toMovieDto(updatedMovie);
    }

    @Override
    public void deleteById(Integer id) {
        movieRepository.deleteById(id);
    }
}
