package com.robertlippai.brickset_tracker_api.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrUpdateBrickBrandRequestDto {
    private String name;
    private String description;
}
