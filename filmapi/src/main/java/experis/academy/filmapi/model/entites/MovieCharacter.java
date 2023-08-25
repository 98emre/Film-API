package experis.academy.filmapi.model.entites;

import java.util.Set;

import experis.academy.filmapi.utilites.enums.Gender;
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
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
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

    @Column(name = "picture_url", length = 200)
    private String pictureURL;

    @ManyToMany(mappedBy = "characters", cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    private Set<Movie> movies;

}
