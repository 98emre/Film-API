package experis.academy.filmapi.model.dto.franchise;

import java.util.Set;

import lombok.Data;

@Data
public class FranchiseDTO {
    private int id;
    private String name;
    private String description;
    private Set<Integer> movieIds;
}