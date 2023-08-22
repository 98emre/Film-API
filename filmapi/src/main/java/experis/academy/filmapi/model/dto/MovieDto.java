package experis.academy.filmapi.model.dto;

import java.util.Set;

import lombok.Data;

@Data
public class MovieDto {

    private int id;
    private String title;
    private String genre;
    private int releaseYear;
    private String director;
    private String posterPictureURL;
    private String trailerLink;
}
