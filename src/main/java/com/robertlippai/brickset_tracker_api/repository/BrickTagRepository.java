package com.robertlippai.brickset_tracker_api.repository;

import com.robertlippai.brickset_tracker_api.api.model.BrickTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrickTagRepository extends JpaRepository<BrickTag, Integer> {

}
