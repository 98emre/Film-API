package com.example.filmapi.repository;

import com.example.filmapi.model.entites.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FranchiseRepository extends JpaRepository<Franchise, Integer> {

}
