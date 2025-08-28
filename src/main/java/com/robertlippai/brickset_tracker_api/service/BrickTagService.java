package com.robertlippai.brickset_tracker_api.service;

import com.robertlippai.brickset_tracker_api.api.dto.BrickTagDto;
import com.robertlippai.brickset_tracker_api.api.dto.CreateOrUpdateBrickTagRequestDto;
import com.robertlippai.brickset_tracker_api.api.model.BrickTag;
import com.robertlippai.brickset_tracker_api.repository.BrickTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrickTagService {
    private final BrickTagRepository brickTagRepository;

    @Autowired
    public BrickTagService(BrickTagRepository brickTagRepository) {
        this.brickTagRepository = brickTagRepository;
    }

    public Optional<BrickTagDto> getBrickTag(int id) {
        return brickTagRepository.findById(id).map(BrickTagDto::fromEntity);
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
    public Optional<BrickTagDto> updateBrickTag(int id, CreateOrUpdateBrickTagRequestDto updatedBrickTag) {
        return brickTagRepository.findById(id)
                .map(existingTag -> {
                    existingTag.setName(updatedBrickTag.getName());
                    var savedTag = brickTagRepository.save(existingTag);
                    return BrickTagDto.fromEntity(savedTag);
                });
    }

    @Transactional
    public boolean deleteBrickTag(int id) {
        if (brickTagRepository.existsById(id)) {
            brickTagRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
