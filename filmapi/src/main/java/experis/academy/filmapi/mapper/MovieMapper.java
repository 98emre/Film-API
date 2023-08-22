package experis.academy.filmapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import experis.academy.filmapi.model.Movie;
import experis.academy.filmapi.model.dto.MovieDto;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    @Mapping(source = "franchise.id", target = "franchiseId")
    @Mapping(source = "characters", target = "characterIds")
    MovieDto toMovieDto(Movie movie);

    @Mapping(source = "franchiseId", target = "franchise.id")
    @Mapping(source = "characterIds", target = "characters")
    Movie toMovie(MovieDto movieDto);
}
