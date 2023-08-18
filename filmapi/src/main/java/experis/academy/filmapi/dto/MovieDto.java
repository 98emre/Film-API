package experis.academy.filmapi.dto;

import java.util.Set;

public class MovieDto {

    private int id;
    private String title;
    private String genre;
    private int releaseYear;
    private String director;
    private String posterPictureURL;
    private String trailerLink;
    private Set<CharacterDto> characters;
    private FranchiseDto franchise;

    public MovieDto(int id, String title, String genre, int releaseYear, String director, String posterPictureURL,
            String trailerLink, Set<CharacterDto> characters, FranchiseDto franchise) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.director = director;
        this.posterPictureURL = posterPictureURL;
        this.trailerLink = trailerLink;
        this.characters = characters;
        this.franchise = franchise;
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

    public Set<CharacterDto> getCharacters() {
        return characters;
    }

    public void setCharacters(Set<CharacterDto> characters) {
        this.characters = characters;
    }

    public FranchiseDto getFranchise() {
        return franchise;
    }

    public void setFranchise(FranchiseDto franchise) {
        this.franchise = franchise;
    }

}
