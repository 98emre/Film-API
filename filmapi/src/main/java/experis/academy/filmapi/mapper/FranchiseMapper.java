package experis.academy.filmapi.mapper;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import experis.academy.filmapi.model.Franchise;
import experis.academy.filmapi.model.Movie;
import experis.academy.filmapi.model.dto.franchise.FranchiseDTO;
import experis.academy.filmapi.model.dto.franchise.FranchisePostDTO;
import experis.academy.filmapi.model.dto.franchise.FranchiseUpdateDTO;

@Mapper(componentModel = "spring")
public abstract class FranchiseMapper {

    public abstract Franchise franchisePostDtoToFranchise(FranchisePostDTO franchisepDto);

    public abstract Franchise franchiseUpdateDtoToFranchise(FranchiseUpdateDTO franchiseUpdateDTO);

    public abstract Collection<FranchiseDTO> franchisesToFranchisesDto(Collection<Franchise> franchises);

    @Mapping(target = "movieIds", source = "franchise.movies", qualifiedByName = "moviesToIds")
    public abstract FranchiseDTO franchiseToFranchiseDto(Franchise franchise);

    @Named("moviesToIds")
    Set<Integer> map(Set<Movie> source) {
        if (source == null)
            return null;
        return source.stream().map(m -> m.getId()).collect(Collectors.toSet());
    }

}
