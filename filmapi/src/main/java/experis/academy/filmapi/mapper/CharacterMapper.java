package experis.academy.filmapi.mapper;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import experis.academy.filmapi.model.Character;
import experis.academy.filmapi.model.Movie;
import experis.academy.filmapi.model.dto.CharacterDto;

@Mapper(componentModel = "spring")
public interface CharacterMapper {

    CharacterDto toCharacterDto(Character character);

    Character toCharacter(CharacterDto characterDto);

}
