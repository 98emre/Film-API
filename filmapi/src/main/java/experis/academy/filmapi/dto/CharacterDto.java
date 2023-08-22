package experis.academy.filmapi.dto;

import java.util.Set;

import experis.academy.filmapi.model.Gender;

public class CharacterDTO {
    private int id;
    private String name;
    private String alias;
    private Gender gender;
    private String pictureURL;
    private Set<MovieDTO> movies;

    public CharacterDTO(int id, String name, String alias, Gender gender, String pictureURL, Set<MovieDTO> movies) {
        this.id = id;
        this.name = name;
        this.alias = alias;
        this.gender = gender;
        this.pictureURL = pictureURL;
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

    public Set<MovieDTO> getMovies() {
        return movies;
    }

    public void setMovies(Set<MovieDTO> movies) {
        this.movies = movies;
    }
}
