package experis.academy.filmapi.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "character")
public class Character {

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

    @Column(name = "picture_url", length = 200)
    private String pictureURL;

    @ManyToMany(mappedBy = "characters")
    @JsonIgnore
    private Set<Movie> movies;
}
