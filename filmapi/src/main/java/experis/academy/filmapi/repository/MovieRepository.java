package experis.academy.filmapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import experis.academy.filmapi.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

}
