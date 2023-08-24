package experis.academy.filmapi.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SqlResultSetMappings;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private int id;

    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @Column(name = "genre", length = 100)
    private String genre;

    @Column(name = "release_year", nullable = false)
    private int releaseYear;

    @Column(length = 50, nullable = false)
    private String director;

    @Column(name = "picture_url", length = 1000)
    private String posterPictureURL;

    @Column(name = "trailer_link", length = 1000)
    private String trailerLink;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "movie_character", joinColumns = { @JoinColumn(name = "movie_id") }, inverseJoinColumns = {
            @JoinColumn(name = "character_id")
    })

    private Set<Character> characters;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "franchise_id")
    private Franchise franchise;



}
