package com.robertlippai.brickset_tracker_api.api.controller;

import com.robertlippai.brickset_tracker_api.api.dto.BrickBrandDto;
import com.robertlippai.brickset_tracker_api.api.dto.CreateOrUpdateBrickBrandRequestDto;
import com.robertlippai.brickset_tracker_api.service.BrickBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        Optional<BrickBrandDto> brickBrand = brickBrandService.getBrickBrand(id);

        return brickBrand.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public BrickBrandDto createBrickBrand(@RequestBody CreateOrUpdateBrickBrandRequestDto brickBrandRequestDto) {
        return brickBrandService.createBrickBrand(brickBrandRequestDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrickBrandDto> updateBrickBrand(@PathVariable int id, @RequestBody CreateOrUpdateBrickBrandRequestDto brickBrandRequestDto) {
        return brickBrandService.updateBrickBrand(id, brickBrandRequestDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrickBrand(@PathVariable int id) {
        if (brickBrandService.deleteBrickBrand(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
