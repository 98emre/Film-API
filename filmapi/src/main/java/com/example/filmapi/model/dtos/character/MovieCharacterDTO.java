package com.example.filmapi.model.dtos.character;

import java.util.Set;

import com.example.filmapi.utilites.enums.Gender;
import lombok.Data;

@Data
public class MovieCharacterDTO {
    private int id;
    private String name;
    private String alias;
    private Gender gender;
    private String pictureURL;
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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public Set<Integer> getMovieIds() {
        return movieIds;
    }

    public void setMovieIds(Set<Integer> movieIds) {
        this.movieIds = movieIds;
    }
}
