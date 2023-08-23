package experis.academy.filmapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import experis.academy.filmapi.model.Character;
import experis.academy.filmapi.model.Movie;
import experis.academy.filmapi.model.dto.CharacterDto;
import experis.academy.filmapi.model.dto.MovieDto;
import experis.academy.filmapi.service.CharacterService;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = CharacterMapper.class)
public interface MovieMapper {

    MovieDto toMovieDto(Movie movie);

    Movie toMovie(MovieDto movieDto);
}
