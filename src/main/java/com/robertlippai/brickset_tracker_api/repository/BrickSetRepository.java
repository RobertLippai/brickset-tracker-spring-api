package com.robertlippai.brickset_tracker_api.repository;

import com.robertlippai.brickset_tracker_api.api.model.BrickSet;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrickSetRepository extends JpaRepository<BrickSet, Integer> {
    @EntityGraph(attributePaths = {"brand", "tags"})
    List<BrickSet> findByBrandName(String brandName);

    @EntityGraph(attributePaths = {"brand", "tags"})
    @Override
    List<BrickSet> findAll();
}
