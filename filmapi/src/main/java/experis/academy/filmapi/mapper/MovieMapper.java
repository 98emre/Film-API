package experis.academy.filmapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import experis.academy.filmapi.model.dtos.movie.MovieDTO;
import experis.academy.filmapi.model.dtos.movie.MoviePostDTO;
import experis.academy.filmapi.model.dtos.movie.MovieUpdateDTO;
import experis.academy.filmapi.model.entites.Character;
import experis.academy.filmapi.model.entites.Movie;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = CharacterMapper.class)
public abstract class MovieMapper {

    public abstract Movie moviePostDtoToMovie(MoviePostDTO moviepPostDTO);

    public abstract Movie movieUpdateDtoToMovie(MovieUpdateDTO movieUpdateDTO);

    public abstract Collection<MovieDTO> moviesToMoviesDto(Collection<Movie> movies);

    public abstract Collection<MoviePostDTO> moviesToMoviesPostDto(Collection<Movie> movies);

    @Mapping(target = "characterIds", source = "characters", qualifiedByName = "charactersToIds")
    @Mapping(target = "franchiseId", source = "franchise.id")
    public abstract MovieDTO movieToMovieDto(Movie movie);

    @Named("charactersToIds")
    Set<Integer> map(Set<Character> source) {
        if (source == null) {
            return null;
        }

        return source.stream().map(ch -> ch.getId()).collect(Collectors.toSet());
    }

}
