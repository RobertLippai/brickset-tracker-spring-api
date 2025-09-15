package com.robertlippai.brickset_tracker_api.api.controller;

import com.robertlippai.brickset_tracker_api.api.dto.brand.BrickBrandDto;
import com.robertlippai.brickset_tracker_api.api.dto.brand.CreateOrUpdateBrickBrandRequestDto;
import com.robertlippai.brickset_tracker_api.service.BrickBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
public class BrickBrandController {
    private final BrickBrandService brickBrandService;

    @Autowired
    public BrickBrandController(BrickBrandService brickBrandService) {
        this.brickBrandService = brickBrandService;
    }

    @GetMapping
    public List<BrickBrandDto> getAllBrickBrands(){
        return brickBrandService.getBrickBrands();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrickBrandDto> getBrickBrand(@PathVariable int id){
        return ResponseEntity.ok(brickBrandService.getBrickBrand(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('EDITOR')")
    public BrickBrandDto createBrickBrand(@RequestBody CreateOrUpdateBrickBrandRequestDto brickBrandRequestDto) {
        return brickBrandService.createBrickBrand(brickBrandRequestDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('EDITOR')")
    public ResponseEntity<BrickBrandDto> updateBrickBrand(@PathVariable int id, @RequestBody CreateOrUpdateBrickBrandRequestDto brickBrandRequestDto) {
        return ResponseEntity.ok(brickBrandService.updateBrickBrand(id, brickBrandRequestDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('EDITOR')")
    public ResponseEntity<Void> deleteBrickBrand(@PathVariable int id) {
        brickBrandService.deleteBrickBrand(id);
        return ResponseEntity.noContent().build();
    }
}
