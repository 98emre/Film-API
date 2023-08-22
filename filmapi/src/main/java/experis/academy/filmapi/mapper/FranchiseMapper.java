package experis.academy.filmapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import experis.academy.filmapi.model.Franchise;
import experis.academy.filmapi.model.dto.FranchiseDto;

@Mapper(componentModel = "spring")
public interface FranchiseMapper {

    FranchiseMapper INSTANCE = Mappers.getMapper(FranchiseMapper.class);

    FranchiseDto toFranchiseDto(Franchise franchise);

    Franchise toFranchise(FranchiseDto franchiseDto);

}
