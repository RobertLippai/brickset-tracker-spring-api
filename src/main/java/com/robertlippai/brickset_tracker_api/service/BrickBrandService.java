package com.robertlippai.brickset_tracker_api.service;

import com.robertlippai.brickset_tracker_api.api.dto.BrickBrandDto;
import com.robertlippai.brickset_tracker_api.api.dto.CreateOrUpdateBrickBrandRequestDto;
import com.robertlippai.brickset_tracker_api.api.model.BrickBrand;
import com.robertlippai.brickset_tracker_api.repository.BrickBrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrickBrandService {
    private final BrickBrandRepository brickBrandRepository;

    @Autowired
    public BrickBrandService(BrickBrandRepository brickBrandRepository) {
        this.brickBrandRepository = brickBrandRepository;
    }

    public Optional<BrickBrandDto> getBrickBrand(int id) {
        return brickBrandRepository.findById(id).map(BrickBrandDto::fromEntity);
    }
    public List<BrickBrandDto> getBrickBrands() {
        return brickBrandRepository.findAll().stream().map(BrickBrandDto::fromEntity).collect(Collectors.toList());
    }

    public BrickBrandDto createBrickBrand(CreateOrUpdateBrickBrandRequestDto brickBrandRequestDto) {
        BrickBrand brand = new BrickBrand();
        brand.setName(brickBrandRequestDto.getName());
        brand.setDescription(brickBrandRequestDto.getDescription());

        var newBrandEntity = brickBrandRepository.save(brand);

        return BrickBrandDto.fromEntity(newBrandEntity);
    }

    public Optional<BrickBrandDto> updateBrickBrand(int id, CreateOrUpdateBrickBrandRequestDto updatedBrickBrand) {
        return brickBrandRepository.findById(id)
                .map(existingBrand -> {
                    existingBrand.setName(updatedBrickBrand.getName());
                    existingBrand.setDescription(updatedBrickBrand.getDescription());
                    return brickBrandRepository.save(existingBrand);
                }).map(BrickBrandDto::fromEntity);
    }

    public boolean deleteBrickBrand(int id) {
        if (brickBrandRepository.existsById(id)) {
            brickBrandRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
