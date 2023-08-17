package experis.academy.filmapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import experis.academy.filmapi.model.Franchise;

@Repository
public interface FranchiseRepository extends JpaRepository<Franchise, Integer> {

}
