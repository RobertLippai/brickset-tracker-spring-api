package com.robertlippai.brickset_tracker_api.service;

import com.robertlippai.brickset_tracker_api.api.model.BrickSet;
import com.robertlippai.brickset_tracker_api.repository.BrickSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrickSetService {

    private final BrickSetRepository brickSetRepository;

    @Autowired
    public BrickSetService(BrickSetRepository brickSetRepository) {
        this.brickSetRepository = brickSetRepository;
    }

    public Optional<BrickSet> getBrickSet(int id) {
        return brickSetRepository.findById(id);
    }
    public List<BrickSet> getBrickSets() {
        return brickSetRepository.findAll();
    }

    public BrickSet createBrickSet(BrickSet brickSet) {
        return brickSetRepository.save(brickSet);
    }
    public Optional<BrickSet> updateBrickSet(int id, BrickSet updatedBrickSet) {
        return brickSetRepository.findById(id)
                .map(existingSet -> {
                    existingSet.setName(updatedBrickSet.getName());
                    existingSet.setSetId(updatedBrickSet.getSetId());
                    existingSet.setPieces(updatedBrickSet.getPieces());
                    existingSet.setReleaseYear(updatedBrickSet.getReleaseYear());
                    existingSet.setImageUrl(updatedBrickSet.getImageUrl());
                    existingSet.setDescription(updatedBrickSet.getDescription());
                    existingSet.setInstructionUrl(updatedBrickSet.getInstructionUrl());
                    existingSet.setMinifiguresCount(updatedBrickSet.getMinifiguresCount());
                    return brickSetRepository.save(existingSet);
                });
    }
    public boolean deleteBrickSet(int id) {
        if (brickSetRepository.existsById(id)) {
            brickSetRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
