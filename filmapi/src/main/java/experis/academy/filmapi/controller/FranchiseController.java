package experis.academy.filmapi.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import experis.academy.filmapi.model.dto.FranchiseDto;
import experis.academy.filmapi.service.FranchiseService;

@RestController
@RequestMapping(path = "api/franchise")
public class FranchiseController {

    private final FranchiseService franchiseService;

    @Autowired
    public FranchiseController(FranchiseService franchiseService) {
        this.franchiseService = franchiseService;
    }

    @GetMapping
    public ResponseEntity<Collection<FranchiseDto>> getAll() {
        try {
            return ResponseEntity.ok(franchiseService.findAll());
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return ResponseEntity.status(500).body(null); // It's better to send a 500 status for server errors.
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<FranchiseDto> findById(@PathVariable int id) {
        try {
            FranchiseDto franchise = franchiseService.findById(id);
            if (franchise != null) {
                return ResponseEntity.ok(franchise);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<FranchiseDto> addFranchise(@RequestBody FranchiseDto franchiseDto) {
        return ResponseEntity.ok(franchiseService.add(franchiseDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FranchiseDto> updateFranchise(@PathVariable Integer id,
            @RequestBody FranchiseDto franchiseDto) {
        if (franchiseService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }

        franchiseDto.setId(id);
        return ResponseEntity.ok(franchiseService.update(franchiseDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFranchise(@PathVariable Integer id) {
        try {
            franchiseService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return ResponseEntity.status(500).build();
        }
    }
}
