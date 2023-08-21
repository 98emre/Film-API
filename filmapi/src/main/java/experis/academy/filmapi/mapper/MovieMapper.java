package experis.academy.filmapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import experis.academy.filmapi.dto.MovieDto;
import experis.academy.filmapi.model.Movie;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    @Mapping(source = "franchise.id", target = "franchiseId")
    MovieDto modelToDto(Movie movie);

    @Mapping(source = "franchiseId", target = "franchise.id")
    Movie dtoToModel(MovieDto movieDto);
}