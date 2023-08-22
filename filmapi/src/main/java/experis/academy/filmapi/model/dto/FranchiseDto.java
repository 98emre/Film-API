package experis.academy.filmapi.model.dto;

import java.util.Set;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class FranchiseDto {
    private int id;
    private String name;
    private String description;
    private Set<Integer> movieIds;
}