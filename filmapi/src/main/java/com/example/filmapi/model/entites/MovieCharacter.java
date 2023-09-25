package com.example.filmapi.model.entites;

import java.util.Set;

import com.example.filmapi.utilites.enums.Gender;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "character")
public class MovieCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "character_id")
    private int id;

    @Column(name = "character_name", length = 50, nullable = false)
    private String name;

    @Column(name = "alias", length = 50)
    private String alias;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "picture_url", length = 1000)
    private String pictureURL;

    @ManyToMany(mappedBy = "characters", cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    private Set<Movie> movies;

    public MovieCharacter() {
    }

    public MovieCharacter(int id, String name, String alias, Gender gender, String pictureURL) {
        this.id = id;
        this.name = name;
        this.alias = alias;
        this.gender = gender;
        this.pictureURL = pictureURL;
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

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }
}
