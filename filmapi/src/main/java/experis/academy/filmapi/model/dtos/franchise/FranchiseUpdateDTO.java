package experis.academy.filmapi.model.dtos.franchise;

import lombok.Data;

@Data
public class FranchiseUpdateDTO {
    private int id;
    private String name;
    private String description;
}
