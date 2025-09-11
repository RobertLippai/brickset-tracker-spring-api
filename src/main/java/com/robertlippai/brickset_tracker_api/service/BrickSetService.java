package com.robertlippai.brickset_tracker_api.service;

import com.robertlippai.brickset_tracker_api.api.dto.set.BrickSetDto;
import com.robertlippai.brickset_tracker_api.api.dto.set.CreateOrUpdateBrickSetRequestDto;
import com.robertlippai.brickset_tracker_api.api.model.BrickBrand;
import com.robertlippai.brickset_tracker_api.api.model.BrickSet;
import com.robertlippai.brickset_tracker_api.api.model.BrickTag;
import com.robertlippai.brickset_tracker_api.repository.BrickBrandRepository;
import com.robertlippai.brickset_tracker_api.repository.BrickSetRepository;
import com.robertlippai.brickset_tracker_api.repository.BrickTagRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public BrickSetDto createBrickSet(CreateOrUpdateBrickSetRequestDto dto) {
        BrickBrand brand = brickBrandRepository.findById(dto.getBrandId()).orElseThrow(
                () -> new EntityNotFoundException("BrickBrand not found with id: " + dto.getBrandId())
        );

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

    @Transactional
    public Optional<BrickSetDto> updateBrickSet(int id, CreateOrUpdateBrickSetRequestDto updatedBrickSet) {
        BrickBrand newBrand = brickBrandRepository.findById(updatedBrickSet.getBrandId()).orElseThrow(
                () -> new EntityNotFoundException("BrickBrand not found with id: " + updatedBrickSet.getBrandId())
        );

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

    @Transactional
    public boolean deleteBrickSet(int id) {
        if (brickSetRepository.existsById(id)) {
            brickSetRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public BrickSetDto addTagToBrickSet(int brickSetId, int tagId) {
        BrickSet brickSetEntity = brickSetRepository.findById(brickSetId).orElseThrow(
                () -> new EntityNotFoundException("BrickSet not found with id: " + brickSetId)
        );
        BrickTag brickTagEntity = brickTagRepository.findById(tagId).orElseThrow(
                () -> new EntityNotFoundException("BrickTag not found with id: " + tagId)
        );

        brickSetEntity.addTag(brickTagEntity);
        BrickSet savedEntity = brickSetRepository.save(brickSetEntity);

        return BrickSetDto.fromEntity(savedEntity);
    }

    @Transactional
    public void removeTagFromBrickSet(int brickSetId, int tagId) {
        BrickSet brickSetEntity = brickSetRepository.findById(brickSetId).orElseThrow(
                () -> new EntityNotFoundException("BrickSet not found with id: " + brickSetId)
        );

        BrickTag brickTagEntity = brickTagRepository.findById(tagId).orElseThrow(
                () -> new EntityNotFoundException("BrickTag not found with id: " + tagId)
        );

        brickSetEntity.removeTag(brickTagEntity);

        brickSetRepository.save(brickSetEntity);
    }
}
