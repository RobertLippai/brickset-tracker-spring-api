package com.robertlippai.brickset_tracker_api.service;

import com.robertlippai.brickset_tracker_api.api.dto.tag.BrickTagDto;
import com.robertlippai.brickset_tracker_api.api.dto.tag.CreateOrUpdateBrickTagRequestDto;
import com.robertlippai.brickset_tracker_api.api.model.BrickTag;
import com.robertlippai.brickset_tracker_api.repository.BrickTagRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrickTagService {
    private final BrickTagRepository brickTagRepository;

    @Autowired
    public BrickTagService(BrickTagRepository brickTagRepository) {
        this.brickTagRepository = brickTagRepository;
    }

    public BrickTagDto getBrickTag(int id) {
        return brickTagRepository.findById(id).map(BrickTagDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("BrickTag with id " + id + " not found"));
    }

    public List<BrickTagDto> getBrickTags() {
        return brickTagRepository.findAll().stream().map(BrickTagDto::fromEntity).collect(Collectors.toList());
    }

    @Transactional
    public BrickTagDto createBrickTag(CreateOrUpdateBrickTagRequestDto brickTagRequestDto) {
        BrickTag brickTag = new BrickTag();
        brickTag.setName(brickTagRequestDto.getName());

        var newTagEntity = brickTagRepository.save(brickTag);

        return BrickTagDto.fromEntity(newTagEntity);
    }

    @Transactional
    public BrickTagDto updateBrickTag(int id, CreateOrUpdateBrickTagRequestDto updatedBrickTag) {
        BrickTag existingTag = brickTagRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("BrickTag with id " + id + " not found"));

        existingTag.setName(updatedBrickTag.getName());

        BrickTag savedTag = brickTagRepository.save(existingTag);
        return BrickTagDto.fromEntity(savedTag);
    }

    @Transactional
    public void deleteBrickTag(int id) {
        if (!brickTagRepository.existsById(id)) {
            throw new EntityNotFoundException("BrickTag not found with id: " + id);
        }
        brickTagRepository.deleteById(id);
    }
}
