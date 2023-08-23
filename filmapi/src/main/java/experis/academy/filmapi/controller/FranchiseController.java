package experis.academy.filmapi.controller;

import java.net.URI;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import experis.academy.filmapi.mapper.FranchiseMapper;
import experis.academy.filmapi.model.Franchise;
import experis.academy.filmapi.model.dto.franchise.FranchiseDTO;
import experis.academy.filmapi.model.dto.franchise.FranchisePostDTO;
import experis.academy.filmapi.model.dto.franchise.FranchiseUpdateDTO;
import experis.academy.filmapi.service.FranchiseService;

@RestController
@RequestMapping(path = "api/franchise")
public class FranchiseController {

    private final FranchiseService franchiseService;
    private final FranchiseMapper franchiseMapper;

    @Autowired
    public FranchiseController(FranchiseService franchiseService, FranchiseMapper franchiseMapper) {
        this.franchiseService = franchiseService;
        this.franchiseMapper = franchiseMapper;
    }

    @GetMapping
    public ResponseEntity<Collection<FranchiseDTO>> getAll() {
        return ResponseEntity.ok(franchiseMapper.franchisesToFranchisesDto(franchiseService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FranchiseDTO> findById(@PathVariable int id) {
        return ResponseEntity.ok(franchiseMapper.franchiseToFranchiseDto(franchiseService.findById(id)));
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addFranchise(@RequestBody FranchisePostDTO franchisePostDTO) {
        Franchise franchise = franchiseService.add(franchiseMapper.franchisePostDtoToFranchise(franchisePostDTO));
        URI location = URI.create("franchise/" + franchise.getId());

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateFranchise(@PathVariable Integer id,
            @RequestBody FranchiseUpdateDTO franchiseUpdateDTO) {

        franchiseUpdateDTO.setId(id);
        franchiseService.update(franchiseMapper.franchiseUpdateDtoToFranchise(franchiseUpdateDTO));

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFranchiseById(@PathVariable Integer id) {
        franchiseService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
