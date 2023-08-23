package experis.academy.filmapi.serviceImpl;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import experis.academy.filmapi.mapper.CharacterMapper;
import experis.academy.filmapi.model.Character;
import experis.academy.filmapi.model.dto.character.CharacterDTO;
import experis.academy.filmapi.repository.CharacterRepository;
import experis.academy.filmapi.service.CharacterService;

@Service
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepository characterRepository;

    @Autowired
    public CharacterServiceImpl(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @Override
    public Character findById(Integer id) {
        return characterRepository.findById(id).orElse(null);
    }

    @Override
    public Collection<Character> findAll() {
        return characterRepository.findAll().stream()
                .sorted(Comparator.comparingInt(Character::getId))
                .collect(Collectors.toList());
    }

    @Override
    public Character add(Character character) {
        return characterRepository.save(character);
    }

    @Override
    public Character update(Character character) {
        if (!characterRepository.existsById(character.getId())) {
            return null; // or throw an exception
        }

        return characterRepository.save(character);
    }

    @Override
    public void deleteById(Integer id) {
        characterRepository.deleteById(id);
    }

}
