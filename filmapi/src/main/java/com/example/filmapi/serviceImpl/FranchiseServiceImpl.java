package com.example.filmapi.serviceImpl;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.filmapi.model.entites.*;
import com.example.filmapi.repository.*;
import com.example.filmapi.service.FranchiseService;
import com.example.filmapi.utilites.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FranchiseServiceImpl implements FranchiseService {

    private final FranchiseRepository franchiseRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public FranchiseServiceImpl(FranchiseRepository franchiseRepository, MovieRepository movieRepository) {
        this.franchiseRepository = franchiseRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public Collection<Franchise> findAll() {
        return franchiseRepository.findAll().stream()
                .sorted(Comparator.comparing(Franchise::getId))
                .collect(Collectors.toList());
    }

    @Override
    public Franchise findById(Integer id) {
        return franchiseRepository.findById(id).orElseThrow(() -> new FranchiseNotFoundException(id));
    }

    @Override
    public Franchise add(Franchise franchise) {
        return franchiseRepository.save(franchise);
    }

    @Override
    public Franchise update(Franchise franchise) {

        Franchise updateFranchise = franchiseRepository.findById(franchise.getId())
                .orElseThrow(() -> new FranchiseNotFoundException(franchise.getId()));

        if (franchise.getName() != null) {
            updateFranchise.setName(franchise.getName());
        }

        if (franchise.getDescription() != null) {
            updateFranchise.setDescription(franchise.getDescription());
        }

        return franchiseRepository.save(updateFranchise);
    }

    @Override
    public void deleteById(Integer id) {
        Collection<Movie> franchiseMovies = findAllMoviesByFranchise(id);
        if (franchiseMovies == null) {
            throw new FranchiseNotFoundException(id);
        }

        for (Movie movie : franchiseMovies) {
            movie.setFranchise(null);
            movieRepository.save(movie);
        }

        franchiseRepository.deleteById(id);
    }

    @Override
    public Set<Movie> findAllMoviesByFranchise(int franchiseId) {
        Franchise franchise = franchiseRepository.findById(franchiseId)
                .orElseThrow(() -> new FranchiseNotFoundException(franchiseId));
        return franchise.getMovies();
    }

    @Override
    public Franchise addMovies(int franchiseId, Set<Integer> movieIds) {

        Franchise franchise = franchiseRepository.findById(franchiseId)
                .orElseThrow(() -> new FranchiseNotFoundException(franchiseId));

        for (int id : movieIds) {
            Movie movie = movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
            movie.setFranchise(franchise);
        }

        return franchiseRepository.save(franchise);
    }

    @Override
    public Franchise removeMovies(int franchiseId, Set<Integer> movieIds) {
        Franchise franchise = franchiseRepository.findById(franchiseId)
                .orElseThrow(() -> new FranchiseNotFoundException(franchiseId));

        Set<Movie> currentFranchiseMovies = franchise.getMovies();

        for (int id : movieIds) {
            Movie movie = movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
            movie.setFranchise(null);
        }

        for (Movie m : currentFranchiseMovies) {
            if (!movieIds.contains(m.getId())) {
                Movie movie = movieRepository.findById(m.getId())
                        .orElseThrow(() -> new MovieNotFoundException(m.getId()));
                movie.setFranchise(franchise);
            }
        }

        return franchiseRepository.save(franchise);
    }

    @Override
    public Set<MovieCharacter> findAllCharactersByFranchise(int franchiseId) {
        if (!franchiseRepository.existsById(franchiseId)) {
            throw new FranchiseNotFoundException(franchiseId);
        }

        Set<MovieCharacter> characters = new HashSet<>();
        Set<Movie> movies = findAllMoviesByFranchise(franchiseId);

        for (Movie m : movies) {
            characters.addAll(m.getCharacters());
        }

        return characters;

    }

}
