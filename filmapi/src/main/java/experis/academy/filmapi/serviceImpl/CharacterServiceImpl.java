package experis.academy.filmapi.serviceImpl;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import experis.academy.filmapi.model.MovieCharacter;
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
    public MovieCharacter findById(Integer id) {
        return characterRepository.findById(id).orElse(null);
    }

    @Override
    public Collection<MovieCharacter> findAll() {
        return characterRepository.findAll().stream().sorted(Comparator.comparingInt(MovieCharacter::getId))
                .collect(Collectors.toList());
    }

    @Override
    public MovieCharacter add(MovieCharacter entity) {
        return characterRepository.save(entity);
    }

    @Override
    public MovieCharacter update(MovieCharacter entity) {
        return characterRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        characterRepository.deleteById(id);
    }

}
