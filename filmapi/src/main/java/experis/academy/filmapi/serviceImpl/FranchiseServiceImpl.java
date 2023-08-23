package experis.academy.filmapi.serviceImpl;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import experis.academy.filmapi.model.Franchise;
import experis.academy.filmapi.repository.FranchiseRepository;
import experis.academy.filmapi.service.FranchiseService;

@Service
public class FranchiseServiceImpl implements FranchiseService {

    private final FranchiseRepository franchiseRepository;

    @Autowired
    public FranchiseServiceImpl(FranchiseRepository franchiseRepository) {
        this.franchiseRepository = franchiseRepository;
    }

    @Override
    public Collection<Franchise> findAll() {
        return franchiseRepository.findAll().stream()
                .sorted(Comparator.comparing(Franchise::getId))
                .collect(Collectors.toList());
    }

    @Override
    public Franchise findById(Integer id) {
        return franchiseRepository.findById(id).orElse(null);
    }

    @Override
    public Franchise add(Franchise franchise) {
        return franchiseRepository.save(franchise);
    }

    @Override
    public Franchise update(Franchise franchise) {
        return franchiseRepository.save(franchise);
    }

    @Override
    public void deleteById(Integer id) {
        franchiseRepository.deleteById(id);
    }
}
