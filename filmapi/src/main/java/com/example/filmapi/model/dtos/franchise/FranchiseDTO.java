package com.example.filmapi.model.dtos.franchise;

import java.util.Set;
import lombok.Data;

@Data
public class FranchiseDTO {
    private int id;
    private String name;
    private String description;
    private Set<Integer> movieIds;

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

    public Set<Integer> getMovieIds() {
        return movieIds;
    }

    public void setMovieIds(Set<Integer> movieIds) {
        this.movieIds = movieIds;
    }
}