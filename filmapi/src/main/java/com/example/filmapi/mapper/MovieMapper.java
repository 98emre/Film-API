package com.example.filmapi.mapper;

import com.example.filmapi.model.dtos.movie.*;
import com.example.filmapi.model.entites.*;
import org.mapstruct.*;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = MovieCharacterMapper.class)
public abstract class MovieMapper {

    public abstract Movie moviePostDtoToMovie(MoviePostDTO moviepPostDTO);

    public abstract Movie movieUpdateDtoToMovie(MovieUpdateDTO movieUpdateDTO);

    public abstract Collection<MovieDTO> moviesToMoviesDto(Collection<Movie> movies);

    public abstract Collection<MoviePostDTO> moviesToMoviesPostDto(Collection<Movie> movies);

    @Mapping(target = "characterIds", source = "characters", qualifiedByName = "charactersToIds")
    @Mapping(target = "franchiseId", source = "franchise.id")
    public abstract MovieDTO movieToMovieDto(Movie movie);

    @Named("charactersToIds")
    Set<Integer> map(Set<MovieCharacter> source) {
        if (source == null) {
            return null;
        }

        return source.stream().map(ch -> ch.getId()).collect(Collectors.toSet());
    }

}
