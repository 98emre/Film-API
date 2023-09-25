package com.example.filmapi.mapper;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.filmapi.model.dtos.character.*;
import com.example.filmapi.model.entites.MovieCharacter;
import com.example.filmapi.model.entites.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


@Mapper(componentModel = "spring")
public abstract class MovieCharacterMapper {

    public abstract MovieCharacter characterPostToCharacter(MovieCharacterPostDTO characterPostDTO);

    public abstract MovieCharacter characterUpdateToCharacter(MovieCharacterUpdateDTO characterUpdateDTO);

    @Mapping(target = "movieIds", source = "movies", qualifiedByName = "moviesToIds")
    public abstract MovieCharacterDTO characterToCharacterDTO(MovieCharacter character);

    public abstract Collection<MovieCharacterDTO> charactersToCharactersDTO(Collection<MovieCharacter> characters);

    public abstract Collection<MovieCharacterPostDTO> charactersToCharactersPostDTO(
            Collection<MovieCharacter> characters);

    @Named("moviesToIds")
    Set<Integer> map(Set<Movie> source) {
        if (source == null)
            return null;
        return source.stream().map(m -> m.getId()).collect(Collectors.toSet());
    }

}
