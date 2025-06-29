package com.robertlippai.brickset_tracker_api.repository;

import com.robertlippai.brickset_tracker_api.api.model.BrickSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrickSetRepository extends JpaRepository<BrickSet, Integer> {

}
