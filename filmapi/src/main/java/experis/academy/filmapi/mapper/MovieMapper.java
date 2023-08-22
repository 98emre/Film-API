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
public abstract class MovieMapper {

    @Autowired
    protected CharacterService characterService;

    @Autowired
    protected CharacterMapper characterMapper;

    @Mapping(target = "characters", source = "characters")
    @Mapping(target = "franchise.id", source = "franchise.id")
    public abstract MovieDto toMovieDto(Movie movie);

    @Mapping(target = "characters", source = "characters")
    @Mapping(target = "franchise.id", source = "franchise.id")
    public abstract Movie toMovie(MovieDto movieDto);
}
