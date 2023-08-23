package experis.academy.filmapi.serviceImpl;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import experis.academy.filmapi.model.Franchise;
import experis.academy.filmapi.model.Movie;
import experis.academy.filmapi.repository.FranchiseRepository;
import experis.academy.filmapi.repository.MovieRepository;
import experis.academy.filmapi.service.FranchiseService;

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
        return franchiseRepository.findById(id).orElse(null);
    }

    @Override
    public Franchise add(Franchise franchise) {
        return franchiseRepository.save(franchise);
    }

    @Override
    public Franchise update(Franchise franchise) {
        return franchiseRepository.save(franchise);
    }

    @Override
    public void deleteById(Integer id) {
        franchiseRepository.deleteById(id);
    }

    @Override
    public Set<Movie> findAllMoviesByFranchise(int franchiseId) {
        if (!franchiseRepository.existsById(franchiseId)) {
            return null;
        }
        Franchise franchise = franchiseRepository.findById(franchiseId).orElse(null);
        return franchise.getMovies();
    }

    @Override
    public Franchise updateMovies(int franchiseId, Set<Integer> moviesId) {
        if (!franchiseRepository.existsById(franchiseId)) {
            return null;
        }
        
        Franchise franchise = franchiseRepository.findById(franchiseId).orElse(null);
            Set<Movie> movies = new HashSet<>();

            for (int id: moviesId) {
                if (movieRepository.existsById(id)) {
                    Movie movie = movieRepository.findById(id).orElse(null);
                    movies.add(movie);
                }
            }
            franchise.setMovies(movies);
        return franchiseRepository.save(franchise);
    }
}

