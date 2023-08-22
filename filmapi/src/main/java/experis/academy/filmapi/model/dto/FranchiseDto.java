package experis.academy.filmapi.model.dto;

import java.util.Set;

public class FranchiseDto {
    private int id;
    private String name;
    private String description;
    private Set<MovieDto> movies;

    public FranchiseDto(int id, String name, String description, Set<MovieDto> movies) {
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

    public Set<MovieDto> getMovies() {
        return movies;
    }

    public void setMovies(Set<MovieDto> movies) {
        this.movies = movies;
    }

}
