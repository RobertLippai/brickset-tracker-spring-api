package com.robertlippai.brickset_tracker_api.service;

import com.robertlippai.brickset_tracker_api.api.dto.BrickSetDto;
import com.robertlippai.brickset_tracker_api.api.dto.CreateOrUpdateBrickSetRequestDto;
import com.robertlippai.brickset_tracker_api.api.model.BrickBrand;
import com.robertlippai.brickset_tracker_api.api.model.BrickSet;
import com.robertlippai.brickset_tracker_api.api.model.BrickTag;
import com.robertlippai.brickset_tracker_api.repository.BrickBrandRepository;
import com.robertlippai.brickset_tracker_api.repository.BrickSetRepository;
import com.robertlippai.brickset_tracker_api.repository.BrickTagRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrickSetService {

    private final BrickSetRepository brickSetRepository;
    private final BrickBrandRepository brickBrandRepository;
    private final BrickTagRepository brickTagRepository;

    @Autowired
    public BrickSetService(BrickSetRepository brickSetRepository, BrickBrandRepository brickBrandRepository, BrickTagRepository brickTagRepository) {
        this.brickSetRepository = brickSetRepository;
        this.brickBrandRepository = brickBrandRepository;
        this.brickTagRepository = brickTagRepository;
    }

    public Optional<BrickSetDto> getBrickSet(int id) {
        return brickSetRepository.findById(id).map(BrickSetDto::fromEntity);
    }

    public List<BrickSetDto> getBrickSets() {
        return brickSetRepository.findAll().stream().map(BrickSetDto::fromEntity).collect(Collectors.toList());
    }

    public BrickSetDto createBrickSet(CreateOrUpdateBrickSetRequestDto dto) {
        BrickBrand brand = brickBrandRepository.findById(dto.getBrandId()).orElseThrow();

        BrickSet newBrickSet = new BrickSet();
        newBrickSet.setSetId(dto.getSetId());
        newBrickSet.setName(dto.getName());
        newBrickSet.setPieces(dto.getPieces());
        newBrickSet.setReleaseYear(dto.getReleaseYear());
        newBrickSet.setImageUrl(dto.getImageUrl());
        newBrickSet.setDescription(dto.getDescription());
        newBrickSet.setInstructionUrl(dto.getInstructionUrl());
        newBrickSet.setMinifiguresCount(dto.getMinifiguresCount());
        newBrickSet.setBrand(brand);

        var newSetEntity = brickSetRepository.save(newBrickSet);

        return BrickSetDto.fromEntity(newSetEntity);
    }

    public Optional<BrickSetDto> updateBrickSet(int id, CreateOrUpdateBrickSetRequestDto updatedBrickSet) {
        BrickBrand newBrand = brickBrandRepository.findById(updatedBrickSet.getBrandId()).orElseThrow();

        return brickSetRepository.findById(id)
                .map(existingSet -> {
                    existingSet.setSetId(updatedBrickSet.getSetId());
                    existingSet.setName(updatedBrickSet.getName());
                    existingSet.setPieces(updatedBrickSet.getPieces());
                    existingSet.setReleaseYear(updatedBrickSet.getReleaseYear());
                    existingSet.setImageUrl(updatedBrickSet.getImageUrl());
                    existingSet.setDescription(updatedBrickSet.getDescription());
                    existingSet.setInstructionUrl(updatedBrickSet.getInstructionUrl());
                    existingSet.setMinifiguresCount(updatedBrickSet.getMinifiguresCount());
                    existingSet.setBrand(newBrand);
                    return brickSetRepository.save(existingSet);
                }).map(BrickSetDto::fromEntity);
    }

    public boolean deleteBrickSet(int id) {
        if (brickSetRepository.existsById(id)) {
            brickSetRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public BrickSetDto addTagToBrickSet(int brickSetId, int tagId) {
        BrickSet brickSetEntity = brickSetRepository.findById(brickSetId).orElseThrow(
                () -> new EntityNotFoundException("BrickSet not found with id: " + brickSetId)
        );
        BrickTag brickTagEntity = brickTagRepository.findById(tagId).orElseThrow(
                () -> new EntityNotFoundException("BrickTag not found with id: " + brickSetId)
        );

        brickSetEntity.addTag(brickTagEntity);
        BrickSet savedEntity = brickSetRepository.save(brickSetEntity);

        return BrickSetDto.fromEntity(savedEntity);
    }

    public void removeTagFromBrickSet(int brickSetId, int tagId) {
        BrickSet brickSetEntity = brickSetRepository.findById(brickSetId).orElseThrow(
                () -> new EntityNotFoundException("BrickSet not found with id: " + brickSetId)
        );

        BrickTag brickTagEntity = brickTagRepository.findById(tagId).orElseThrow(
                () -> new EntityNotFoundException("BrickTag not found with id: " + brickSetId)
        );

        brickSetEntity.removeTag(brickTagEntity);

        brickSetRepository.save(brickSetEntity);
    }
}
