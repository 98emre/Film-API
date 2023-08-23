package experis.academy.filmapi.mapper;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import experis.academy.filmapi.model.Character;
import experis.academy.filmapi.model.Movie;
import experis.academy.filmapi.model.dto.character.CharacterDTO;
import experis.academy.filmapi.model.dto.character.CharacterPostDTO;
import experis.academy.filmapi.model.dto.character.CharacterUpdateDTO;

@Mapper(componentModel = "spring")
public abstract class CharacterMapper {

    public abstract Character characterPostToCharacter(CharacterPostDTO characterPostDTO);

    public abstract Character characterUpdateToCharacter(CharacterUpdateDTO characterUpdateDTO);

    @Mapping(target = "movieIds", source = "movies", qualifiedByName = "moviesToIds")
    public abstract CharacterDTO toCharacterDTO(Character character);

    public abstract Collection<CharacterDTO> toCharacterDTO(Collection<Character> characters);

    @Named("moviesToIds")
    Set<Integer> map(Set<Movie> source) {
        if (source == null)
            return null;
        return source.stream().map(m -> m.getId()).collect(Collectors.toSet());
    }

}
