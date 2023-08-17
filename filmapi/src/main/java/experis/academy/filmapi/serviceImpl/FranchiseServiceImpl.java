package experis.academy.filmapi.serviceImpl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import experis.academy.filmapi.entity.Franchise;
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
    public Franchise findById(Integer id) {
        return franchiseRepository.findById(id).orElse(null);
    }

    @Override
    public Collection<Franchise> findAll() {
        return franchiseRepository.findAll();
    }

    @Override
    public Franchise add(Franchise entity) {
        return franchiseRepository.save(entity);
    }

    @Override
    public Franchise update(Franchise entity) {
        return franchiseRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        franchiseRepository.deleteById(id);
    }

}
