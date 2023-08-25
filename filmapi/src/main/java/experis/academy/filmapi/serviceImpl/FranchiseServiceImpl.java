package experis.academy.filmapi.serviceImpl;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import experis.academy.filmapi.model.entites.MovieCharacter;
import experis.academy.filmapi.model.entites.Franchise;
import experis.academy.filmapi.model.entites.Movie;
import experis.academy.filmapi.repository.FranchiseRepository;
import experis.academy.filmapi.repository.MovieRepository;
import experis.academy.filmapi.service.FranchiseService;
import experis.academy.filmapi.utilites.exceptions.FranchiseNotFoundException;
import experis.academy.filmapi.utilites.exceptions.MovieNotFoundException;

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
        if (!franchiseRepository.existsById(franchise.getId())) {
            throw new FranchiseNotFoundException(franchise.getId());
        }

        return franchiseRepository.save(franchise);
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
    public Franchise updateMovies(int franchiseId, Set<Integer> moviesId) {

        Franchise franchise = franchiseRepository.findById(franchiseId)
                .orElseThrow(() -> new FranchiseNotFoundException(franchiseId));

        Set<Movie> movies = new HashSet<>();

        for (int id : moviesId) {
            if (movieRepository.existsById(id)) {
                Movie movie = movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
                movie.setFranchise(franchise);
                movies.add(movie);
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
