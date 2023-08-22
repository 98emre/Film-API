package experis.academy.filmapi.mappers;

import org.mapstruct.Mapper;

import experis.academy.filmapi.dto.MovieDTO;
import experis.academy.filmapi.model.Movie;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    MovieDTO toMovieDto(Movie movie);
    Movie toMovie(MovieDTO dto);
    
}
