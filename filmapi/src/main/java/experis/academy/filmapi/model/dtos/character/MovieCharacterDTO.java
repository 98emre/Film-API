package experis.academy.filmapi.model.dtos.character;

import java.util.Set;

import experis.academy.filmapi.utilites.enums.Gender;
import lombok.Data;

@Data
public class MovieCharacterDTO {
    private int id;
    private String name;
    private String alias;
    private Gender gender;
    private String pictureURL;
    private Set<Integer> movieIds;
}
