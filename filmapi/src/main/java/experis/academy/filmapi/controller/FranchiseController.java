package experis.academy.filmapi.controller;

import java.net.URI;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import experis.academy.filmapi.service.FranchiseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import experis.academy.filmapi.entity.Franchise;

@RestController
@RequestMapping(path = "api/franchises")
public class FranchiseController {

    private final FranchiseService franchiseService;
    
    @Autowired
    public FranchiseController(FranchiseService franchiseService) {
        this.franchiseService = franchiseService;
    }

    @GetMapping
    public ResponseEntity<List<Franchise>> getAll() {
        return ResponseEntity.ok((List<Franchise>) franchiseService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Franchise> getById(@PathVariable int id) {
        return ResponseEntity.ok(franchiseService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Franchise> add(@RequestBody Franchise franchise) {
        Franchise franchiseResponse = franchiseService.add(franchise);
        URI location = URI.create("franchises/" + franchiseResponse.getId());
        return ResponseEntity.created(location).build();
    }


    //T update(T entity);

    @Operation
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Franchise successfully deleted"),
        @ApiResponse(responseCode = "400", description = "Malformed request"),
        @ApiResponse(responseCode = "404", description = "Franchise not found with supplied ID")
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Franchise> deleteById(@PathVariable int id) {
        franchiseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
