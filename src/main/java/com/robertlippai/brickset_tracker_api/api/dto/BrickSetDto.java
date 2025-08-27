package com.robertlippai.brickset_tracker_api.api.dto;

import com.robertlippai.brickset_tracker_api.api.model.BrickSet;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrickSetDto {
    private int sid;
    private String setId;
    private String name;
    private Integer pieces;
    private Integer releaseYear;
    private String imageUrl;
    private String description;
    private String instructionUrl;
    private Integer minifiguresCount;
    private String brandName;

    public static BrickSetDto fromEntity(BrickSet brickSet) {
        BrickSetDto dto = new BrickSetDto();

        dto.setSid(brickSet.getSid());
        dto.setSetId(brickSet.getSetId());
        dto.setName(brickSet.getName());
        dto.setPieces(brickSet.getPieces());
        dto.setReleaseYear(brickSet.getReleaseYear());
        dto.setImageUrl(brickSet.getImageUrl());
        dto.setDescription(brickSet.getDescription());
        dto.setInstructionUrl(brickSet.getInstructionUrl());
        dto.setMinifiguresCount(brickSet.getMinifiguresCount());

        if (brickSet.getBrand() != null) {
            dto.setBrandName(brickSet.getBrand().getName());
        }

        return dto;
    }
}
