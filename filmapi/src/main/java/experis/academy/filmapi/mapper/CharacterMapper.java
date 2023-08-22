package experis.academy.filmapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import experis.academy.filmapi.model.Character;
import experis.academy.filmapi.model.dto.CharacterDto;

@Mapper(componentModel = "spring")
public interface CharacterMapper {

    CharacterMapper INSTANCE = Mappers.getMapper(CharacterMapper.class);

    CharacterDto toCharacterDto(Character character);

    Character toCharacter(CharacterDto characterDto);
}
