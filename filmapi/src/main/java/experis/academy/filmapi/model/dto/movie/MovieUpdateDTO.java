package experis.academy.filmapi.model.dto.movie;

import lombok.Data;

@Data
public class MovieUpdateDTO {
    private int id;
    private String title;
    private String genre;
    private int releaseYear;
    private String director;
    private String posterPictureURL;
    private String trailerLink;
}
