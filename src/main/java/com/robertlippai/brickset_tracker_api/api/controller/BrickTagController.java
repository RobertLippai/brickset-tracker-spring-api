package com.robertlippai.brickset_tracker_api.api.controller;

import com.robertlippai.brickset_tracker_api.api.dto.tag.BrickTagDto;
import com.robertlippai.brickset_tracker_api.api.dto.tag.CreateOrUpdateBrickTagRequestDto;
import com.robertlippai.brickset_tracker_api.service.BrickTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        Optional<BrickTagDto> brickTag = brickTagService.getBrickTag(id);

        return brickTag.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public BrickTagDto createBrickTag(@RequestBody CreateOrUpdateBrickTagRequestDto brickTagRequestDto) {
        return brickTagService.createBrickTag(brickTagRequestDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrickTagDto> updateBrickTag(@PathVariable int id, @RequestBody CreateOrUpdateBrickTagRequestDto brickTagRequestDto) {
        return brickTagService.updateBrickTag(id, brickTagRequestDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrickTag(@PathVariable int id) {
        if (brickTagService.deleteBrickTag(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
