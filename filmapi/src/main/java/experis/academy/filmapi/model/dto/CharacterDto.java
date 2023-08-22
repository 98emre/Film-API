package experis.academy.filmapi.model.dto;

import java.util.Set;

import experis.academy.filmapi.model.Gender;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CharacterDto {
    private int id;

    private String name;

    private String alias;

    private Gender gender;

    private String pictureURL;

    private Set<Integer> movieIds;
}
