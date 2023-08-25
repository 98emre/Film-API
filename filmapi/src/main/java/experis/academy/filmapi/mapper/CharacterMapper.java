package experis.academy.filmapi.mapper;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import experis.academy.filmapi.model.dtos.character.CharacterDTO;
import experis.academy.filmapi.model.dtos.character.CharacterPostDTO;
import experis.academy.filmapi.model.dtos.character.CharacterUpdateDTO;
import experis.academy.filmapi.model.entites.Character;
import experis.academy.filmapi.model.entites.Movie;

@Mapper(componentModel = "spring")
public abstract class CharacterMapper {

    public abstract Character characterPostToCharacter(CharacterPostDTO characterPostDTO);

    public abstract Character characterUpdateToCharacter(CharacterUpdateDTO characterUpdateDTO);

    @Mapping(target = "movieIds", source = "movies", qualifiedByName = "moviesToIds")
    public abstract CharacterDTO characterToCharacterDTO(Character character);

    public abstract Collection<CharacterDTO> charactersToCharactersDTO(Collection<Character> characters);

    public abstract Collection<CharacterPostDTO> charactersToCharactersPostDTO(Collection<Character> characters);

    @Named("moviesToIds")
    Set<Integer> map(Set<Movie> source) {
        if (source == null)
            return null;
        return source.stream().map(m -> m.getId()).collect(Collectors.toSet());
    }

}
