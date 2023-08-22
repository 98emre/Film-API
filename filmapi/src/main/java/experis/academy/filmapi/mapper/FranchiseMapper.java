package experis.academy.filmapi.mapper;

import org.mapstruct.Mapper;

import experis.academy.filmapi.model.Franchise;
import experis.academy.filmapi.model.dto.FranchiseDto;

@Mapper(componentModel = "spring")
public interface FranchiseMapper {
    FranchiseDto toFranchiseDto(Franchise franchise);

    Franchise toFranchise(FranchiseDto franchiseDto);

}
