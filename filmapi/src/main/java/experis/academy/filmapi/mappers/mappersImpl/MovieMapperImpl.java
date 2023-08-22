package experis.academy.filmapi.mappers.mappersImpl;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.HashSet;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import experis.academy.filmapi.dto.MovieDTO;
import experis.academy.filmapi.mappers.MovieMapper;
import experis.academy.filmapi.model.MovieCharacter;
import experis.academy.filmapi.model.Franchise;
import experis.academy.filmapi.model.Movie;

@Component
public class MovieMapperImpl implements MovieMapper {

    @Override
    public MovieDTO toMovieDto(Movie movie) {
        if (movie == null) {
            return null;
        }

        MovieDTO dto = new MovieDTO();
        dto.setId(movie.getId());
        dto.setTitle(movie.getTitle());
        if (movie.getGenre() != null) {
            dto.setGenre(movie.getGenre());
        }
        dto.setReleaseYear(movie.getReleaseYear());
        dto.setDirector(movie.getDirector());
        if (movie.getPosterPictureURL() != null) {
            dto.setPosterPictureURL(movie.getPosterPictureURL());
        }
        if (movie.getTrailerLink() != null) {
            dto.setTrailerLink(movie.getTrailerLink());
        }
        Set<MovieCharacter> characters = movie.getCharacters();
        if (characters != null) {
            Set<Integer> charactersIds = new HashSet<>();
            for (MovieCharacter character : characters) {
                charactersIds.add(character.getId());
            }
            // dto.setCharacters(charactersIds);
            // dto.setSubjects(student.getSubjects().stream()
            //     .map(s -> s.getId()).collect(Collectors.toSet()));
        }
        Franchise franchise = movie.getFranchise();
        if (franchise != null) {
            dto.setFranchise(franchise.getId());
        }
        return dto;
    }

    @Override
    public Movie toMovie(MovieDTO dto) {
        Movie movie = new Movie();
        movie.setId(dto.getId());
        movie.setTitle(dto.getTitle());
        movie.setReleaseYear(dto.getReleaseYear());
        movie.setDirector(dto.getDirector());
        movie.setPosterPictureURL(dto.getPosterPictureURL());
        movie.setTrailerLink(dto.getTrailerLink());
        return movie;
    }
    
}
