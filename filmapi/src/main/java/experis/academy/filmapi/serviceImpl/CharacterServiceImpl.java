package experis.academy.filmapi.serviceImpl;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import experis.academy.filmapi.mapper.CharacterMapper;
import experis.academy.filmapi.model.Character;
import experis.academy.filmapi.model.dto.CharacterDto;
import experis.academy.filmapi.repository.CharacterRepository;
import experis.academy.filmapi.service.CharacterService;

@Service
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @Autowired
    public CharacterServiceImpl(CharacterRepository characterRepository, CharacterMapper characterMapper) {
        this.characterRepository = characterRepository;
        this.characterMapper = characterMapper;
    }

    @Override
    public CharacterDto findById(Integer id) {
        return characterRepository.findById(id).map(characterMapper::toCharacterDto).orElse(null);
    }

    @Override
    public Collection<CharacterDto> findAll() {
        return characterRepository.findAll().stream()
                .sorted(Comparator.comparingInt(Character::getId))
                .map(characterMapper::toCharacterDto)
                .collect(Collectors.toList());
    }

    @Override
    public CharacterDto add(CharacterDto characterDto) {
        Character character = characterMapper.toCharacter(characterDto);
        Character savedCharacter = characterRepository.save(character);
        return characterMapper.toCharacterDto(savedCharacter);
    }

    @Override
    public CharacterDto update(CharacterDto characterDto) {
        if (!characterRepository.existsById(characterDto.getId())) {
            return null; // or throw an exception
        }
        Character character = characterMapper.toCharacter(characterDto);
        Character updatedCharacter = characterRepository.save(character);
        return characterMapper.toCharacterDto(updatedCharacter);
    }

    @Override
    public void deleteById(Integer id) {
        characterRepository.deleteById(id);
    }

}
