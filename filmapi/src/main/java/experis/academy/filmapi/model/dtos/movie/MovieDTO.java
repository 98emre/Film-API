package experis.academy.filmapi.model.dtos.movie;

import java.util.Set;
import lombok.Data;

@Data
public class MovieDTO {

    private int id;
    private String title;
    private String genre;
    private int releaseYear;
    private String director;
    private String posterPictureURL;
    private String trailerLink;
    private Integer franchiseId;
    private Set<Integer> characterIds;
}
