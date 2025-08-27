package com.robertlippai.brickset_tracker_api.api.controller;

import com.robertlippai.brickset_tracker_api.api.dto.BrickSetDto;
import com.robertlippai.brickset_tracker_api.api.dto.CreateOrUpdateBrickSetRequestDto;
import com.robertlippai.brickset_tracker_api.service.BrickSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sets")
public class BrickSetController {

    private final BrickSetService brickSetService;

    @Autowired
    public BrickSetController(BrickSetService brickSetService) {
        this.brickSetService = brickSetService;
    }

    @GetMapping
    public List<BrickSetDto> getAllBrickSets(){
        return brickSetService.getBrickSets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrickSetDto> getBrickSet(@PathVariable int id){
        Optional<BrickSetDto> brickSet = brickSetService.getBrickSet(id);

        return brickSet.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public BrickSetDto createBrickSet(@RequestBody CreateOrUpdateBrickSetRequestDto brickSetRequestDto) {
        return brickSetService.createBrickSet(brickSetRequestDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrickSetDto> updateBrickSet(@PathVariable int id, @RequestBody CreateOrUpdateBrickSetRequestDto brickSetRequestDto) {
        return brickSetService.updateBrickSet(id, brickSetRequestDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrickSet(@PathVariable int id) {
        if (brickSetService.deleteBrickSet(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
