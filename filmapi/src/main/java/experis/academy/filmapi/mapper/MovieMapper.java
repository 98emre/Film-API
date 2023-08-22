package experis.academy.filmapi.mapper;

import org.mapstruct.Mapper;

import experis.academy.filmapi.model.Movie;
import experis.academy.filmapi.model.dto.MovieDto;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    MovieDto toMovieDto(Movie movie);

    Movie toMovie(MovieDto movieDto);

}