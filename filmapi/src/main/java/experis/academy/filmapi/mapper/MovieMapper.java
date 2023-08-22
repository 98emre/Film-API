package experis.academy.filmapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import experis.academy.filmapi.model.Movie;
import experis.academy.filmapi.model.dto.MovieDto;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    MovieDto toMovieDto(Movie movie);

    Movie toMovie(MovieDto movieDto);
}
