package experis.academy.filmapi.controller;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import experis.academy.filmapi.model.Franchise;
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
    public ResponseEntity<Collection<Franchise>> getAll() {
        try {
            return ResponseEntity.ok(franchiseService.findAll());
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Franchise> findById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(franchiseService.findById(id));
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Franchise> addFranchise(@RequestBody Franchise franchise) {
        return ResponseEntity.ok(franchiseService.add(franchise));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Franchise> updateFranchise(@PathVariable Integer id, @RequestBody Franchise franchise) {
        if (franchiseService.findById(id) == null) {
            return null;
        }

        Franchise updatedFranchise = franchiseService.findById(id);
        updatedFranchise.setDescription(franchise.getDescription());
        updatedFranchise.setName(franchise.getName());

        return ResponseEntity.ok(franchiseService.update(updatedFranchise));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteFranchise(@PathVariable Integer id) {
        franchiseService.deleteById(id);
    }
}
