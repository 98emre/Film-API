package experis.academy.filmapi.serviceImpl;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import experis.academy.filmapi.model.entites.MovieCharacter;
import experis.academy.filmapi.repository.MovieCharacterRepository;
import experis.academy.filmapi.service.MovieCharacterService;
import experis.academy.filmapi.utilites.exceptions.MovieCharacterNotFoundException;

@Service
public class MovieCharacterServiceImpl implements MovieCharacterService {

    private final MovieCharacterRepository characterRepository;

    @Autowired
    public MovieCharacterServiceImpl(MovieCharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @Override
    public MovieCharacter findById(Integer id) {
        return characterRepository.findById(id).orElseThrow(() -> new MovieCharacterNotFoundException(id));
    }

    @Override
    public Collection<MovieCharacter> findAll() {
        return characterRepository.findAll().stream()
                .sorted(Comparator.comparingInt(MovieCharacter::getId))
                .collect(Collectors.toList());
    }

    @Override
    public MovieCharacter add(MovieCharacter character) {
        return characterRepository.save(character);
    }

    @Override
    public MovieCharacter update(MovieCharacter character) {
        if (!characterRepository.existsById(character.getId())) {
            throw new MovieCharacterNotFoundException(character.getId());
        }

        return characterRepository.save(character);
    }

    @Override
    public void deleteById(Integer id) {
        if (!characterRepository.existsById(id)) {
            throw new MovieCharacterNotFoundException(id);
        }

        characterRepository.deleteById(id);
    }

}
