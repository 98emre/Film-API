package experis.academy.filmapi.repository;

import org.springframework.stereotype.Repository;

import experis.academy.filmapi.model.Movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {



}
