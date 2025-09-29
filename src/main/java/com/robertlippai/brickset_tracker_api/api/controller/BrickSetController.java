package com.robertlippai.brickset_tracker_api.api.controller;

import com.robertlippai.brickset_tracker_api.api.dto.set.BrickSetDto;
import com.robertlippai.brickset_tracker_api.api.dto.set.CreateOrUpdateBrickSetRequestDto;
import com.robertlippai.brickset_tracker_api.service.BrickSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "${app.cors.allowed-origins}")
@RequestMapping("/api/sets")
public class BrickSetController {

    private final BrickSetService brickSetService;

    @Autowired
    public BrickSetController(BrickSetService brickSetService) {
        this.brickSetService = brickSetService;
    }

    @GetMapping
    public List<BrickSetDto> getAllBrickSets(@RequestParam(name = "brandName", required = false) String brandName){
        return brickSetService.getBrickSets(brandName);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrickSetDto> getBrickSet(@PathVariable int id){
        return ResponseEntity.ok(brickSetService.getBrickSet(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('EDITOR')")
    public BrickSetDto createBrickSet(@RequestBody CreateOrUpdateBrickSetRequestDto brickSetRequestDto) {
        return brickSetService.createBrickSet(brickSetRequestDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('EDITOR')")
    public ResponseEntity<BrickSetDto> updateBrickSet(@PathVariable int id, @RequestBody CreateOrUpdateBrickSetRequestDto brickSetRequestDto) {
        return ResponseEntity.ok(brickSetService.updateBrickSet(id, brickSetRequestDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('EDITOR')")
    public ResponseEntity<Void> deleteBrickSet(@PathVariable int id) {
        brickSetService.deleteBrickSet(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{setId}/tags/{tagId}")
    @PreAuthorize("hasAnyRole('EDITOR')")
    public ResponseEntity<BrickSetDto> addTagToBrickSet(@PathVariable int setId,  @PathVariable int tagId) {
        return ResponseEntity.ok(brickSetService.addTagToBrickSet(setId, tagId));
    }

    @DeleteMapping("/{setId}/tags/{tagId}")
    @PreAuthorize("hasAnyRole('EDITOR')")
    public ResponseEntity<Void> removeTagFromBrickSet(@PathVariable int setId,  @PathVariable int tagId) {
        brickSetService.removeTagFromBrickSet(setId, tagId);
        return ResponseEntity.noContent().build();
    }
}
