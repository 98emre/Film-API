package experis.academy.filmapi.mapper;

import org.mapstruct.Mapper;

import experis.academy.filmapi.model.Character;
import experis.academy.filmapi.model.dto.CharacterDto;

@Mapper(componentModel = "spring")
public interface CharacterMapper {

    CharacterDto toCharacterDto(Character character);

    Character toCharacter(CharacterDto characterDto);
}
