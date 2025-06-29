package com.robertlippai.brickset_tracker_api.api.controller;

import com.robertlippai.brickset_tracker_api.api.model.BrickSet;
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
    public List<BrickSet> getAllBrickSets(){
        return brickSetService.getBrickSets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrickSet> getBrickSet(@PathVariable int id){
        Optional<BrickSet> brickSet = brickSetService.getBrickSet(id);

        return brickSet.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public BrickSet createBrickSet(@RequestBody BrickSet brickSet) {
        return brickSetService.createBrickSet(brickSet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrickSet> updateBrickSet(@PathVariable int id, @RequestBody BrickSet brickSet) {
        return brickSetService.updateBrickSet(id, brickSet)
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
