package experis.academy.filmapi.serviceImpl;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import experis.academy.filmapi.mapper.FranchiseMapper;
import experis.academy.filmapi.model.Franchise;
import experis.academy.filmapi.model.dto.FranchiseDto;
import experis.academy.filmapi.repository.FranchiseRepository;
import experis.academy.filmapi.service.FranchiseService;

@Service
public class FranchiseServiceImpl implements FranchiseService {

    private final FranchiseRepository franchiseRepository;
    private final FranchiseMapper franchiseMapper;

    @Autowired
    public FranchiseServiceImpl(FranchiseRepository franchiseRepository, FranchiseMapper franchiseMapper) {
        this.franchiseRepository = franchiseRepository;
        this.franchiseMapper = franchiseMapper;
    }

    @Override
    public Collection<FranchiseDto> findAll() {
        return franchiseRepository.findAll().stream()
                .sorted(Comparator.comparing(Franchise::getId))
                .map(franchiseMapper::toFranchiseDto)
                .collect(Collectors.toList());
    }

    @Override
    public FranchiseDto findById(Integer id) {
        return franchiseRepository.findById(id)
                .map(franchiseMapper::toFranchiseDto)
                .orElse(null);
    }

    @Override
    public FranchiseDto add(FranchiseDto franchiseDto) {
        Franchise franchise = franchiseMapper.toFranchise(franchiseDto);
        Franchise savedFranchise = franchiseRepository.save(franchise);
        return franchiseMapper.toFranchiseDto(savedFranchise);
    }

    @Override
    public FranchiseDto update(FranchiseDto franchiseDto) {
        Franchise franchise = franchiseMapper.toFranchise(franchiseDto);
        Franchise updatedFranchise = franchiseRepository.save(franchise);
        return franchiseMapper.toFranchiseDto(updatedFranchise);
    }

    @Override
    public void deleteById(Integer id) {
        franchiseRepository.deleteById(id);
    }
}

