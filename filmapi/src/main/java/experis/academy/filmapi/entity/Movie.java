package experis.academy.filmapi.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private int id;

    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @Column(name = "genre", length = 100, nullable = false)
    private String genre;

    @Column(name = "release_year", nullable = false)
    private int releaseYear;

    @Column(length = 50, nullable = false)
    private String director;

    @Column(name = "picture_url", length = 200, nullable = false)
    private String posterPictureURL;

    @Column(name = "trailer_link", length = 200, nullable = false)
    private String trailerLink;

    @ManyToMany
    @JoinTable(name = "movie_character", joinColumns = { @JoinColumn(name = "movie_id") }, inverseJoinColumns = {
            @JoinColumn(name = "character_id")
    })

    private Set<Character> characters;

    @OneToOne
    @JoinColumn(name = "franchise_id")
    private Franchise franchise;

    public Movie(int id, String title, String genre, int releaseYear, String director, String posterPictureURL,
            String trailerLink) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.director = director;
        this.posterPictureURL = posterPictureURL;
        this.trailerLink = trailerLink;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPosterPictureURL() {
        return posterPictureURL;
    }

    public void setPosterPictureURL(String posterPictureURL) {
        this.posterPictureURL = posterPictureURL;
    }

    public String getTrailerLink() {
        return trailerLink;
    }

    public void setTrailerLink(String trailerLink) {
        this.trailerLink = trailerLink;
    }
}
