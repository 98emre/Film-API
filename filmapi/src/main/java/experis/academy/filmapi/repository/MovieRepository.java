package experis.academy.filmapi.repository;

import org.springframework.stereotype.Repository;

import experis.academy.filmapi.model.Movie;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

}
