package com.robertlippai.brickset_tracker_api.api.controller;

import com.robertlippai.brickset_tracker_api.api.dto.tag.BrickTagDto;
import com.robertlippai.brickset_tracker_api.api.dto.tag.CreateOrUpdateBrickTagRequestDto;
import com.robertlippai.brickset_tracker_api.service.BrickTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class BrickTagController {
    private final BrickTagService brickTagService;

    @Autowired
    public BrickTagController(BrickTagService brickTagService) {
        this.brickTagService = brickTagService;
    }

    @GetMapping
    public List<BrickTagDto> getAllBrickTags(){
        return brickTagService.getBrickTags();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrickTagDto> getBrickTag(@PathVariable int id){
        return ResponseEntity.ok(brickTagService.getBrickTag(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('EDITOR')")
    public BrickTagDto createBrickTag(@RequestBody CreateOrUpdateBrickTagRequestDto brickTagRequestDto) {
        return brickTagService.createBrickTag(brickTagRequestDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('EDITOR')")
    public ResponseEntity<BrickTagDto> updateBrickTag(@PathVariable int id, @RequestBody CreateOrUpdateBrickTagRequestDto brickTagRequestDto) {
        return ResponseEntity.ok(brickTagService.updateBrickTag(id, brickTagRequestDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('EDITOR')")
    public ResponseEntity<Void> deleteBrickTag(@PathVariable int id) {
        brickTagService.deleteBrickTag(id);
        return ResponseEntity.noContent().build();
    }
}
