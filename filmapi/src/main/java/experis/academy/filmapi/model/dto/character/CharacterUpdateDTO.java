package experis.academy.filmapi.model.dto.character;

import experis.academy.filmapi.model.Gender;
import lombok.Data;

@Data
public class CharacterUpdateDTO {
    private int id;
    private String name;
    private String alias;
    private Gender gender;
    private String pictureURL;
}
