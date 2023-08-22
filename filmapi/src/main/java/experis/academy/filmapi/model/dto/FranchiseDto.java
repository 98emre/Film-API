package experis.academy.filmapi.model.dto;

import java.util.Set;

public class FranchiseDTO {
    private int id;
    private String name;
    private String description;
    private Set<MovieDTO> movies;

    public FranchiseDTO(int id, String name, String description, Set<MovieDTO> movies) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.movies = movies;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<MovieDTO> getMovies() {
        return movies;
    }

    public void setMovies(Set<MovieDTO> movies) {
        this.movies = movies;
    }

}
