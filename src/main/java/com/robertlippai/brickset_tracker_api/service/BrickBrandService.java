package com.robertlippai.brickset_tracker_api.service;

import com.robertlippai.brickset_tracker_api.api.dto.brand.BrickBrandDto;
import com.robertlippai.brickset_tracker_api.api.dto.brand.CreateOrUpdateBrickBrandRequestDto;
import com.robertlippai.brickset_tracker_api.api.model.BrickBrand;
import com.robertlippai.brickset_tracker_api.repository.BrickBrandRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrickBrandService {
    private final BrickBrandRepository brickBrandRepository;

    @Autowired
    public BrickBrandService(BrickBrandRepository brickBrandRepository) {
        this.brickBrandRepository = brickBrandRepository;
    }

    public BrickBrandDto getBrickBrand(int id) {
        return brickBrandRepository.findById(id).map(BrickBrandDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("BrickBrand with id " + id + " not found"));
    }

    public List<BrickBrandDto> getBrickBrands() {
        return brickBrandRepository.findAll().stream().map(BrickBrandDto::fromEntity).collect(Collectors.toList());
    }

    @Transactional
    public BrickBrandDto createBrickBrand(CreateOrUpdateBrickBrandRequestDto brickBrandRequestDto) {
        BrickBrand brand = new BrickBrand();
        brand.setName(brickBrandRequestDto.getName());
        brand.setDescription(brickBrandRequestDto.getDescription());

        var newBrandEntity = brickBrandRepository.save(brand);

        return BrickBrandDto.fromEntity(newBrandEntity);
    }

    @Transactional
    public BrickBrandDto updateBrickBrand(int id, CreateOrUpdateBrickBrandRequestDto updatedBrickBrand) {
        BrickBrand existingBrand = brickBrandRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("BrickBrand with id " + id + " not found"));

        existingBrand.setName(updatedBrickBrand.getName());
        existingBrand.setDescription(updatedBrickBrand.getDescription());

        BrickBrand savedBrand = brickBrandRepository.save(existingBrand);
        return BrickBrandDto.fromEntity(savedBrand);
    }

    @Transactional
    public void deleteBrickBrand(int id) {
        if (!brickBrandRepository.existsById(id)) {
            throw new EntityNotFoundException("BrickBrand with id " + id + " not found");
        }
        brickBrandRepository.deleteById(id);
    }
}
