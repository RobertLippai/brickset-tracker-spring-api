package com.robertlippai.brickset_tracker_api.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrUpdateBrickSetRequestDto {
    private String setId;
    private String name;
    private Integer pieces;
    private Integer releaseYear;
    private String imageUrl;
    private String description;
    private String instructionUrl;
    private Integer minifiguresCount;
    private int brandId;
}
